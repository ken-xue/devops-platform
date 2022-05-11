package io.kenxue.cicd.application.pipeline.logger.node.service;

import io.kenxue.cicd.application.pipeline.pipeline.command.PipelineExecuteCmdExe;
import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;
import io.kenxue.cicd.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Nodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Service
public class PipelineExecuteLoggerSocketServiceImpl implements PipelineExecuteLoggerSocketService {

    //存放ssh连接信息的map,key=执行记录uuid+&+节点uuid
    private static volatile Map<String, Queue<WebSocketSession>> webSocketConnectionPool = new ConcurrentHashMap<>(2 << 4);

    @Resource
    private PipelineExecuteCmdExe pipelineExecuteCmdExe;
    @Resource
    private NodeExecuteLoggerRepository nodeExecuteLoggerRepository;

    @Override
    public void initConnection(WebSocketSession session) {

        URI uri = session.getUri();

        String query = uri.getQuery();
        //流水线uuid
        String nodeUUID = query.split("&")[0].split("=")[1];
        //执行记录uuid
        String loggerUUID = query.split("&")[1].split("=")[1];

        log.info("socket node uuid = {},logger uuid = {}", nodeUUID, loggerUUID);

        // 判断节点是否还在执行
        String key = String.format("%s&%s", loggerUUID, nodeUUID);

        session.getAttributes().put("key", key);

        Nodes node = pipelineExecuteCmdExe.getExecuteNode(key);

        //1.1 如果不在执行则将从数据库查询出执行记录日志返回
        if (Objects.isNull(node)) {
            NodeLogger logger = nodeExecuteLoggerRepository.getByLoggerUUIDAndNodeUUID(loggerUUID, nodeUUID);
            try {
                session.sendMessage(new TextMessage(Optional.ofNullable(logger).map(v -> v.getLogger()).orElse("Not Found Node Execute Logger...\r\n")));
            } catch (IOException e) {
                e.printStackTrace();
            }
//            close(session);
//            return;
        }
        //1.2 如果在执行则将将之前已经执行的日志返回且现在执行的日志同步（将当前连接加入）

        Queue<WebSocketSession> queue = webSocketConnectionPool.getOrDefault(key, new ConcurrentLinkedQueue<>());
        log.error("加入连接池 key:{} queue:{}", key, queue);
        queue.offer(session);
        webSocketConnectionPool.put(key, queue);
    }

    @Override
    public void recvHandle(String buffer, WebSocketSession session) {

    }

    @Override
    public void sendMessage(String key, String message) {
        Queue<WebSocketSession> webSocketSessions = webSocketConnectionPool.get(key);
        if (webSocketSessions == null||webSocketSessions.isEmpty()){
            log.info("当前 key:{}socket 无连接实例,无需推送",key);
            return;
        }
        log.info("推送节点日志信息:{}", webSocketSessions.size());
        for (WebSocketSession conn : webSocketSessions) {
            synchronized (conn) {
                try {
                    conn.sendMessage(new TextMessage(message.getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void close(WebSocketSession session) {
        log.info("close session:{}", session);
    }
}
