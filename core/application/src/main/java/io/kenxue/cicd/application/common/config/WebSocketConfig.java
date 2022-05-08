package io.kenxue.cicd.application.common.config;

import io.kenxue.cicd.application.machine.terminal.WebSSHWebSocketHandler;
import io.kenxue.cicd.application.machine.terminal.WebSocketInterceptor;
import io.kenxue.cicd.application.pipeline.logger.pipeline.handler.PipelineExecuteLoggerWebSocketHandler;
import io.kenxue.cicd.application.pipeline.pipeline.handler.PipelineExecuteWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
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
    private WebSSHWebSocketHandler webSSHWebSocketHandler;
    @Resource
    private PipelineExecuteWebSocketHandler pipelineExecuteWebSocketHandler;
    @Resource
    private PipelineExecuteLoggerWebSocketHandler pipelineExecuteLoggerWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        //socket通道
        //指定处理器和路径
        webSocketHandlerRegistry
                .addHandler(webSSHWebSocketHandler, "/terminal")
                .addHandler(pipelineExecuteWebSocketHandler,"/pipeline")
                .addHandler(pipelineExecuteLoggerWebSocketHandler,"/logger")
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*");
    }
}
