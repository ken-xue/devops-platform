package io.kenxue.cicd.acl.impl.authorize.handler;

import io.kenxue.cicd.acl.impl.authorize.util.ResponseUtil;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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