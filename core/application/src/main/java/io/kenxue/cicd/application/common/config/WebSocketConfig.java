package io.kenxue.cicd.application.common.config;

import io.kenxue.cicd.application.machine.terminal.WebSSHWebSocketHandler;
import io.kenxue.cicd.application.machine.terminal.WebSocketInterceptor;
import io.kenxue.cicd.application.pipeline.logger.node.handler.PipelineExecuteLoggerWebSocketHandler;
import io.kenxue.cicd.application.pipeline.pipeline.handler.PipelineExecuteWebSocketHandler;
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
                .addHandler(webSSHWebSocketHandler, "/terminal")//ssh连接服务器
                .addHandler(pipelineExecuteWebSocketHandler,"/pipeline")//获取流水线节点执行状态推送
                .addHandler(pipelineExecuteLoggerWebSocketHandler,"/logger")//推送节点执行实时日志
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*");
    }
}
