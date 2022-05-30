package io.kenxue.devops.application.common.socket.service;

import io.kenxue.devops.application.common.socket.handler.WebSocket;
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
public class WebSocketServiceHubImpl implements WebSocketServiceHubI, InitializingBean {

    private final ConcurrentHashMap<String,WebSocketService> webSocketServiceHub = new ConcurrentHashMap<>(2<<4);

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public WebSocketService get(String key) {
        WebSocketService webSocketService = webSocketServiceHub.get(key);
        if (Objects.isNull(webSocketService)){
            throw new RuntimeException(String.format("无法找到指定的socket服务 key=%s",key));
        }
        return webSocketService;
    }
    @Override
    public void afterPropertiesSet() {
        Map<String, Object> webSocketServiceMap = applicationContext.getBeansWithAnnotation(WebSocket.class);
        webSocketServiceMap.values().forEach(v->{
            WebSocketService ws = (WebSocketService)v;
            WebSocket annotation = ws.getClass().getAnnotation(WebSocket.class);
            String name = annotation.value();
            log.info("register webSocketService name:{}",name);
            webSocketServiceHub.put(name,ws);
        });
    }
}
