package io.kenxue.devops.application.common.socket.message;

import java.lang.annotation.*;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebSocketMessageHandler {
    /**
     * 该值必须和websocket前缀匹配
     * @return
     */
    String  value();
}