package io.kenxue.cicd.application.pipeline.logger.node.service;

import io.kenxue.cicd.application.common.socket.handler.WebSocket;
import io.kenxue.cicd.application.common.socket.service.WebSocketService;
import io.kenxue.cicd.application.pipeline.pipeline.command.PipelineExecuteCmdExe;
import io.kenxue.cicd.domain.domain.pipeline.NodeLogger;
import io.kenxue.cicd.domain.repository.pipeline.NodeExecuteLoggerRepository;
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

/**
 * @author 麦奇
 */
@Slf4j
@Service
@WebSocket("logger")
public class PipelineNodeExecuteLoggerSocketServiceImpl implements WebSocketService {

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

        NodeLogger logger = pipelineExecuteCmdExe.getExecuteNode(key);

        //1.1 如果不在执行则将从数据库查询出执行记录日志返回
        if (Objects.isNull(logger)) {
            NodeLogger loggerDO = nodeExecuteLoggerRepository.getByLoggerUUIDAndNodeUUID(loggerUUID, nodeUUID);
            try {
                session.sendMessage(new TextMessage(Optional.ofNullable(loggerDO).map(v -> v.getLogger()).orElse("Not Found Node Execute Logger...\r\n")));
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        //1.2 如果在执行则将将之前已经执行的日志返回且现在执行的日志同步（将当前连接加入）
        else {
            try {
                session.sendMessage(new TextMessage(logger.getSb().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Queue<WebSocketSession> queue = webSocketConnectionPool.getOrDefault(key, new ConcurrentLinkedQueue<>());
        log.info("加入连接池 key:{} queue:{}", key, queue);
        queue.offer(session);
        webSocketConnectionPool.put(key, queue);
    }

    @Override
    public void recvHandle(String buffer, WebSocketSession session) {

    }

    @Override
    public void sendMessage(String key, byte[] message) {
        Queue<WebSocketSession> webSocketSessions = webSocketConnectionPool.get(key);
        if (webSocketSessions == null || webSocketSessions.isEmpty()) {
            log.info("当前 key:{} socket 无连接实例,无需推送", key);
            return;
        }
        log.info("推送节点日志信息,客户端实例个数:{}个", webSocketSessions.size());
        for (WebSocketSession conn : webSocketSessions) {
            synchronized (conn) {
                try {
                    conn.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 关闭连接
     * @param session
     */
    @Override
    public void close(WebSocketSession session) {
        String key = String.valueOf(session.getAttributes().get("key"));
        Queue<WebSocketSession> webSocketSessions = webSocketConnectionPool.get(key);
        if (webSocketSessions == null || webSocketSessions.isEmpty()) {
            log.info("当前 key:{} socket 无连接实例,无需关闭连接", key);
            return;
        }
        webSocketSessions.remove(session);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过key移除全部连接
     * @param key
     */
    @Override
    public void close(String key) {
        Queue<WebSocketSession> webSocketSessions = webSocketConnectionPool.get(key);
        if (webSocketSessions == null || webSocketSessions.isEmpty()) {
            log.info("当前 key:{} socket 无连接实例,无需关闭连接", key);
            return;
        }
        for (WebSocketSession conn : webSocketSessions) {
            try {
                conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
