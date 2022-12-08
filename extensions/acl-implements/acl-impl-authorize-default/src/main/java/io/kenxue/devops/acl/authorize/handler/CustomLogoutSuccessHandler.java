package io.kenxue.devops.acl.authorize.handler;

import io.kenxue.devops.acl.authorize.util.ResponseUtil;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description:
 */
@Component
@Slf4j
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    public CustomLogoutSuccessHandler() {
        super();
    }

    
    public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {

        final String refererUrl = request.getHeader("Referer");

        logger.info(refererUrl);

        ResponseUtil.add(response, Response.success("注销成功"));

        super.onLogoutSuccess(request, response, authentication);
    }

}