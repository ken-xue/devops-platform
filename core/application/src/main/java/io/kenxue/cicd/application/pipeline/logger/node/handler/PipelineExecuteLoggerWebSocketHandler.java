package io.kenxue.cicd.application.pipeline.logger.node.handler;

import io.kenxue.cicd.application.machine.terminal.ConstantPool;
import io.kenxue.cicd.application.pipeline.logger.node.service.PipelineExecuteLoggerSocketService;
import io.kenxue.cicd.application.pipeline.pipeline.socket.PipelineExecuteSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.Resource;


/**
* Pipeline的节点执行日志推送WebSocket处理器
*/
@Component
@Slf4j
public class PipelineExecuteLoggerWebSocketHandler implements WebSocketHandler{

    @Resource
    private PipelineExecuteLoggerSocketService pipelineExecuteLoggerSocketService;

    /**
     * @Description: 用户连接上WebSocket的回调
     * @Param: [webSocketSession]
     * @return: void
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        log.info("用户:{},连接 Pipeline 执行r日志推送 WebSocket", webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        //调用初始化连接
        pipelineExecuteLoggerSocketService.initConnection(webSocketSession);
    }

    /**
     * @Description: 收到消息的回调
     * @Param: [webSocketSession, webSocketMessage]
     * @return: void
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {
        if (webSocketMessage instanceof TextMessage) {
            log.info("用户:{},发送命令:{}", webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY), webSocketMessage);
            //调用service接收消息
            pipelineExecuteLoggerSocketService.recvHandle(((TextMessage) webSocketMessage).getPayload(), webSocketSession);
        } else if (webSocketMessage instanceof BinaryMessage) {
            //后期上传文件
        } else if (webSocketMessage instanceof PongMessage) {
            //pong信息
        } else {
            log.error("Unexpected WebSocket message type: {}", webSocketMessage);
        }
    }

    /**
     * @Description: 出现错误的回调
     * @Param: [webSocketSession, throwable]
     * @return: void
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {
        log.error("数据传输错误");
    }

    /**
     * @Description: 连接关闭的回调
     * @Param: [webSocketSession, closeStatus]
     * @return: void
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        log.info("用户:{}断开 pipeline socket 连接", webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        //调用service关闭连接
        pipelineExecuteLoggerSocketService.close(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
