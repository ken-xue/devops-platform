package io.kenxue.devops.acl.authorize.handler;

import io.kenxue.devops.acl.authorize.util.ResponseUtil;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static io.kenxue.devops.coreclient.exception.code.AuthErrorCode.NEED_LOGIN;

/**
 * @Description: 自定义认证拦截器
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final String headerValue;

    public CustomAuthenticationEntryPoint(String headerValue) {
        this.headerValue = headerValue;
    }

    
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        //response.setHeader(Constant.AUTHORIZATION, this.headerValue);
        ResponseUtil.add(response, Response.of(NEED_LOGIN));
    }
}
