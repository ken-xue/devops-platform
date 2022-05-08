package io.kenxue.cicd.application.pipeline.logger.node.service;

import com.alibaba.fastjson.JSON;
import io.kenxue.cicd.application.pipeline.pipeline.command.PipelineExecuteCmdExe;
import io.kenxue.cicd.application.pipeline.pipeline.socket.PipelineExecuteSocketService;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PushNodeExecuteStatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Service
public class PipelineExecuteLoggerSocketServiceImpl implements PipelineExecuteLoggerSocketService {

    //存放ssh连接信息的map
    private static volatile Map<String, Queue<WebSocketSession>> webSocketConnectionPool = new ConcurrentHashMap<>(2 << 4);

    @Resource
    private PipelineExecuteCmdExe pipelineExecuteCmdExe;

    @Override
    public void initConnection(WebSocketSession session) {
        URI uri = session.getUri();
        String key = uri.getQuery();
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(500);
                session.sendMessage(new TextMessage("pong\r\npong\r\npong\r\npong\r\n".getBytes()));
            }
//            Pipeline pipeline = applicationPipelineExecuteCmdExe.get(key);
//            session.sendMessage(new TextMessage(JSON.toJSONString(pipeline).getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Queue<WebSocketSession> queue = webSocketConnectionPool.getOrDefault(key, new ConcurrentLinkedQueue<>());
//        log.error("加入连接池 key:{} queue:{}", key, queue);
//        queue.offer(session);
//        webSocketConnectionPool.put(key, queue);
//        session.getAttributes().put("key", key);
    }

    @Override
    public void recvHandle(String buffer, WebSocketSession session) {

    }

    @Override
    public void sendMessage(String key, PushNodeExecuteStatusDTO message) {
        Queue<WebSocketSession> webSocketSessions = webSocketConnectionPool.get(key);
        log.error("推送信息:{},node name:{},node status:{}", webSocketSessions.size(), message.getNodes().getName(),message.getNodes().getData().getNodeState());
        for (WebSocketSession conn : webSocketSessions) {
            synchronized(conn){
            try {
                conn.sendMessage(new TextMessage(JSON.toJSONString(message).getBytes()));
                //如果是END节点执行完成主动关闭socket
//                if (NodeEnum.END.getName().equals(message.getNodes().getName())
//                        && NodeExecuteStatus.SUCCESS.getName().equals(message.getNodes().getData().getNodeState()))
//                    close(conn);
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
