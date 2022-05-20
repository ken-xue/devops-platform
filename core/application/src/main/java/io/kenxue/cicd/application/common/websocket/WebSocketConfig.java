package io.kenxue.cicd.application.common.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
* websocket配置
*/
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    @Resource
    private WebSocketCenterHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //socket通道
        //指定处理器和路径
        webSocketHandlerRegistry
                .addHandler(webSocketHandler, "/ws/**")
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*");
    }
}
