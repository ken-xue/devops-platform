package io.kenxue.cicd.application.common.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.Resource;


/**
* WebSocket处理器
*/
@Component
@Slf4j
public class WebSocketCenterHandler implements WebSocketHandler {

    @Resource
    private WebSocketServiceHubI webSocketServiceHubI;

    /**
     * 建立连接
     * @param webSocketSession
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        log.info("用户:{},连接WebSocket", webSocketSession.getAttributes().get(Constant.USER_UUID_KEY));
        //调用初始化连接
        WebSocketService webSocketService = webSocketServiceHubI.get((String) webSocketSession.getAttributes().get(Constant.PROCESSOR_NAME));
        webSocketService.initConnection(webSocketSession);
    }
    /**
     * 收到消息的回调
     * @param webSocketSession
     * @param webSocketMessage
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {
        WebSocketService webSocketService = webSocketServiceHubI.get((String) webSocketSession.getAttributes().get(Constant.PROCESSOR_NAME));
        if (webSocketMessage instanceof TextMessage) {
           webSocketService.recvHandle(((TextMessage) webSocketMessage).getPayload(), webSocketSession);
        } else if (webSocketMessage instanceof BinaryMessage) {
            //后期上传文件
        } else if (webSocketMessage instanceof PongMessage) {
            //pong信息
        } else {
            log.error("Unexpected WebSocket message type: {}", webSocketMessage);
        }
    }

    /**
     * 出现错误的回调
     * @param webSocketSession
     * @param throwable
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {
        log.error("数据传输错误");
    }

    /**
     * 连接关闭的回调
     * @param webSocketSession
     * @param closeStatus
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        log.info("用户:{}断开WebSocket连接", webSocketSession.getAttributes().get(Constant.USER_UUID_KEY));
        //调用service关闭连接
        WebSocketService webSocketService = webSocketServiceHubI.get((String) webSocketSession.getAttributes().get(Constant.PROCESSOR_NAME));
        webSocketService.close(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
