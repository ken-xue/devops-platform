package io.kenxue.cicd.application.common.socket.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 套接字服务实现仓储
 */
@Slf4j
@Service
public class WebSocketMessageHandlerHubImpl implements WebSocketMessageHandlerHubI, InitializingBean {

    private final ConcurrentHashMap<String, WebSocketMessageHandlerI> webSocketServiceHub = new ConcurrentHashMap<>(2<<4);

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public WebSocketMessageHandlerI get(String key) {
        WebSocketMessageHandlerI webSocketMessageHandler = webSocketServiceHub.get(key);
        if (Objects.isNull(webSocketMessageHandler)){
            throw new RuntimeException(String.format("无法找到指定的socket message 处理器服务 key=%s",key));
        }
        return webSocketMessageHandler;
    }
    @Override
    public void afterPropertiesSet() {
        Map<String, Object> webSocketServiceMap = applicationContext.getBeansWithAnnotation(WebSocketMessageHandler.class);
        webSocketServiceMap.values().forEach(v->{
            WebSocketMessageHandlerI ws = (WebSocketMessageHandlerI)v;
            WebSocketMessageHandler annotation = ws.getClass().getAnnotation(WebSocketMessageHandler.class);
            String name = annotation.value();
            log.info("register webSocketService name:{}",name);
            webSocketServiceHub.put(name,ws);
        });
    }
}
