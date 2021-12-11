package io.kenxue.cicd.acl.impl.authorize.filter;


import io.kenxue.cicd.acl.impl.authorize.util.ResponseUtil;
import io.kenxue.cicd.acl.impl.authorize.config.JWTConfig;
import io.kenxue.cicd.acl.impl.authorize.constant.Constant;
import io.kenxue.cicd.acl.impl.authorize.impl.GrantedAuthorityImpl;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.sys.user.UserDTO;
import io.kenxue.cicd.coreclient.exception.code.AuthErrorCode;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 自定义JWT认证过滤器
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(Constant.AUTHORIZATION);
        if (header == null || !header.startsWith(Constant.PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = null;
        long start = System.currentTimeMillis();
        String token = request.getHeader(Constant.AUTHORIZATION);
        if (token == null || token.isEmpty()) {
            ResponseUtil.add(response, Response.error(401, "Token为空"));
            return;
        }
        // parse the token.
        String user;
        try {
            Claims claims = Jwts.parser().setSigningKey(Constant.SIGNING_KEY).parseClaimsJws(token.replace("Bearer ", "")).getBody();
            // token签发时间
            long issuedAt = claims.getIssuedAt().getTime();
            // 当前时间
            long currentTimeMillis = System.currentTimeMillis();
            // token过期时间
            long expirationTime = claims.getExpiration().getTime();
            // 1. 签发时间 < 当前时间 < (签发时间+((token过期时间-token签发时间)/2)) 不刷新token
            // 2. (签发时间+((token过期时间-token签发时间)/2)) < 当前时间 < token过期时间 刷新token并返回给前端
            // 3. token过期时间 < 当前时间 跳转登录，重新登录获取token
            // 验证token时间有效性

            if ((issuedAt + ((expirationTime - issuedAt) / 2)) < currentTimeMillis && currentTimeMillis < expirationTime) {
                // 重新生成token start
                Calendar calendar = Calendar.getInstance();
                Date now = calendar.getTime();
                // 设置签发时间
                calendar.setTime(new Date());
                // 设置过期时间
                calendar.add(Calendar.MINUTE, JWTConfig.getJwtTokenExpireTime());
                Date time = calendar.getTime();
                String refreshToken = Jwts.builder()
                        .setSubject(claims.getSubject())
                        .setIssuedAt(now)//签发时间
                        .setExpiration(time)//过期时间
                        .signWith(SignatureAlgorithm.HS512, Constant.SIGNING_KEY) //采用什么算法是可以自己选择的，不一定非要采用HS512
                        .compact();
                // 重新生成token end
                // 主动刷新token，并返回给前端
                response.setHeader("Access-Control-Expose-Headers",Constant.AUTHORIZATION);
                response.addHeader(Constant.AUTHORIZATION, Constant.PREFIX + refreshToken);
            }
            long end = System.currentTimeMillis();
            log.info("执行时间: {}", (end - start) + " 毫秒");
            user = claims.getSubject();
            if (user != null) {
                String[] split = user.split("-");
                UserThreadContext.set(new UserDTO().setUserId(split[0]));
                String[] permissions = split[1].split(",");
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                for (int i=0; i < permissions.length; i++) {
                    authorities.add(new GrantedAuthorityImpl(permissions[i].replace("[","").replace("]","")));
                }
                authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
        } catch (ExpiredJwtException e) {
            log.error("Token已过期: {} " , e.getMessage());
            ResponseUtil.add(response, Response.of(AuthErrorCode.EXPIRED_TOKEN));
            return;
        } catch (UnsupportedJwtException e) {
            log.error("Token格式错误: {} " , e.getMessage());
            ResponseUtil.add(response, Response.of(AuthErrorCode.ERROR_FORMAT_TOKEN));
            return;
        } catch (MalformedJwtException e) {
            log.error("Token没有被正确构造: {} " , e.getMessage());
            ResponseUtil.add(response, Response.of(AuthErrorCode.ILLEGAL_ARGUMENT));
            return;
        } catch (SignatureException e) {
            log.error("签名失败: {} " , e.getMessage());
            ResponseUtil.add(response, Response.of(AuthErrorCode.SIGNATURE_FAIL));
            return;
        } catch (IllegalArgumentException e) {
            log.error("非法参数异常: {} " , e.getMessage());
            ResponseUtil.add(response, Response.of(AuthErrorCode.ILLEGAL_ARGUMENT));
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
