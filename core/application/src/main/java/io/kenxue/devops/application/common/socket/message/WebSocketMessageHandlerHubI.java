package io.kenxue.devops.application.common.socket.message;

import org.springframework.stereotype.Service;

/**
 *  套接字服务实现仓储
 */
@Service
public interface WebSocketMessageHandlerHubI {
    WebSocketMessageHandlerI get(String key);
}
