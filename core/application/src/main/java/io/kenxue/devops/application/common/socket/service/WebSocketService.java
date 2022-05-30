package io.kenxue.devops.application.common.socket.service;

import org.springframework.web.socket.WebSocketSession;

/**
 * @Description: WebSocket的业务逻辑
 */
public interface WebSocketService {
    /**
     * @Description: 初始化ssh连接
     */
    void initConnection(WebSocketSession session);

    /**
     * @Description: 处理客户端发的数据
     */
    void recvHandle(String buffer, WebSocketSession session);

    /**
     * @Description: 数据写回前端 
     */
    void sendMessage(String key, byte[] buffer);

    /**
     * @Description: 关闭连接
     */
    void close(WebSocketSession session);

    /**
     * 通过key关闭连接
     * @param key
     */
    void close(String key);
}
