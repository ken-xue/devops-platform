package io.kenxue.cicd.application.pipeline.pipeline.service;

import io.kenxue.cicd.application.pipeline.pipeline.socket.PipelineExecuteSocketService;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Nodes;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Service
public class PipelineExecuteSocketServiceImpl implements PipelineExecuteSocketService {

    //存放ssh连接信息的map
    private static Map<String, Queue<WebSocketSession>> webSocketConnectionPool = new ConcurrentHashMap<>(2<<4);

    @Override
    public void initConnection(WebSocketSession session) {
        try {
            session.sendMessage(new TextMessage("hello".getBytes()));
            //TODO:首次连接需要将所有节点信息传输一次
            session.sendMessage(new TextMessage("hello".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        URI uri = session.getUri();
        String key = uri.getQuery();
        Queue<WebSocketSession> queue = webSocketConnectionPool.getOrDefault(key, new ConcurrentLinkedQueue<>());
        log.error("加入连接池 key:{} queue:{}",key,queue);
        queue.offer(session);
        webSocketConnectionPool.put(key,queue);
    }

    @Override
    public void recvHandle(String buffer, WebSocketSession session) {

    }

    @Override
    public void sendMessage(String key,Object message) {
        Queue<WebSocketSession> webSocketSessions = webSocketConnectionPool.get(key);
        log.error("推送信息:{},message:{}",webSocketSessions.size(),((Nodes)(message)).getData());
        for (WebSocketSession conn : webSocketSessions) {
            try {
                conn.sendMessage(new TextMessage(message.toString().getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close(WebSocketSession session) {
        log.info("close session:{}",session);
    }
}
