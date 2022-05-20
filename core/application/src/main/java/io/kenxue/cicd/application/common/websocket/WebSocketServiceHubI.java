package io.kenxue.cicd.application.common.websocket;

import org.springframework.stereotype.Service;

/**
 *  套接字服务实现仓储
 */
@Service
public interface WebSocketServiceHubI {
    WebSocketService get(String key);
}
