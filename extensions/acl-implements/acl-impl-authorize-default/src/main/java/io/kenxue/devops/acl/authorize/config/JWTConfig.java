package io.kenxue.devops.acl.authorize.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTConfig implements InitializingBean {

    private static JWTConfig jwtConfig = null;

    @Value("${jwt.token-expire-time:60}")
    private Integer jwtTokenExpireTime;

    @Override
    public void afterPropertiesSet() {
        jwtConfig = this;
    }

    public static Integer getJwtTokenExpireTime(){
        return jwtConfig.jwtTokenExpireTime;
    }
}
