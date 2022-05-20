package io.kenxue.cicd.application.common.websocket;

import java.lang.annotation.*;

@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebSocket {
    /**
     * 该值必须和websocket前缀匹配
     * @return
     */
    String value() default "";
}