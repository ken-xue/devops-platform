package io.kenxue.cicd.application.pipeline.pipeline.service;

import io.kenxue.cicd.application.common.websocket.WebSocket;
import io.kenxue.cicd.application.common.websocket.WebSocketService;
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
@WebSocket("pipeline")
public class PipelineExecuteSocketServiceImpl implements WebSocketService {

    //存放ssh连接信息的map
    private static volatile Map<String, Queue<WebSocketSession>> webSocketConnectionPool = new ConcurrentHashMap<>(2 << 4);

    @Override
    public void initConnection(WebSocketSession session) {
        URI uri = session.getUri();
        String key = uri.getQuery();
        Queue<WebSocketSession> queue = webSocketConnectionPool.getOrDefault(key, new ConcurrentLinkedQueue<>());
        log.error("加入连接池 key:{} queue:{}", key, queue);
        queue.offer(session);
        webSocketConnectionPool.put(key, queue);
        session.getAttributes().put("key", key);
    }

    @Override
    public void recvHandle(String buffer, WebSocketSession session) {

    }

    @Override
    public void sendMessage(String key, byte[] message) {
        Queue<WebSocketSession> webSocketSessions = webSocketConnectionPool.get(key);
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

    @Override
    public void close(WebSocketSession session) {
        log.info("close session:{}", session);
        webSocketConnectionPool.get(session.getAttributes().get("key")).remove(session);
    }

    @Override
    public void close(String key) {

    }
}
