package io.kenxue.devops.application.common.socket.handler;

import io.kenxue.devops.application.common.socket.constant.Constant;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
import java.util.UUID;

public class WebSocketInterceptor implements HandshakeInterceptor {

    /**
     * 前置调用
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            //生成一个UUID
            String uuid = UUID.randomUUID().toString().replace("-","");
            //将uuid放到WebsocketSession中
            map.put(Constant.USER_UUID_KEY, uuid);
            //获取路径的第一个前缀作为其对应的socket处理器key
            String path = request.getURI().getPath();
            int start = path.indexOf('/',1)+1;
            int index = path.indexOf('/', start);
            int last = index == -1 ? path.length() : index;
            String processorKey = path.substring(start, last);
            map.put(Constant.PROCESSOR_NAME,processorKey);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
