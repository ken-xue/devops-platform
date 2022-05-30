package io.kenxue.devops.adapter.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: 麦奇
 * @Date: 21-11-13 上午10:48
 */
@Target({ ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permissions {
    String value() default "";
}
