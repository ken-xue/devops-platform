package io.kenxue.cicd.application.common.socket.service;

import org.springframework.stereotype.Service;

/**
 *  套接字服务实现仓储
 */
@Service
public interface WebSocketServiceHubI {
    WebSocketService get(String key);
}
