package io.kenxue.devops.acl.authorize.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.gson.io.GsonSerializer;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.kenxue.devops.acl.authorize.AuthorizeService;
import io.kenxue.devops.acl.authorize.config.JWTConfig;
import io.kenxue.devops.acl.authorize.constant.Constant;
import io.kenxue.devops.acl.authorize.dto.CaptchaValidateDTO;
import io.kenxue.devops.acl.authorize.util.ResponseUtil;
import io.kenxue.devops.acl.cache.CacheService;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.user.UserDTO;
import io.kenxue.devops.coreclient.exception.BizException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private AuthorizeService authorizeService;

    private CacheService cacheService;

    public JWTLoginFilter(AuthenticationManager authenticationManager, AuthorizeService authorizeService,CacheService cacheService) {
        this.authenticationManager = authenticationManager;
        this.authorizeService = authorizeService;
        this.cacheService = cacheService;
    }

    // 尝试身份认证(接收并解析用户凭证)
    
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            UserDTO user = new ObjectMapper().readValue(req.getInputStream(), UserDTO.class);
            if (StringUtils.isBlank(user.getUserId()))throw new BizException("账号不能为空");
            if (StringUtils.isBlank(user.getUserPassword()))throw new BizException("密码不能为空");
            if (StringUtils.isBlank(user.getUuid()))throw new BizException("UUID不能为空");
            if (StringUtils.isBlank(user.getCode()))throw new BizException("验证码不能为空");
            if (!authorizeService.captchaValidate(new CaptchaValidateDTO().setCode(user.getCode()).setUuid(user.getUuid()))){
                throw new BizException("验证码错误");
            }
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUserId(),
                            user.getUserPassword(),
                            new ArrayList<>())
            );
        } catch (Exception e) {
            ResponseUtil.add(res, Response.error(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage()));
            return null;
        }
    }

    // 认证成功(用户成功登录后，这个方法会被调用，我们在这个方法里生成token)
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) {
        // builder the token
        String token;
        try {
            // 生成token start
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            // 设置签发时间
            calendar.setTime(new Date());
            //存储到缓存中
            cacheService.set(auth.getName(),auth.getAuthorities());
            // 设置过期时间
            calendar.add(Calendar.MINUTE, JWTConfig.getJwtTokenExpireTime());// 50分钟
            Date time = calendar.getTime();
            Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(Constant.SIGNING_KEY));
            token = Jwts.builder()
                    .setSubject(auth.getName())
                    .setIssuedAt(now)//签发时间
                    .setExpiration(time)//过期时间
                    .signWith(key,SignatureAlgorithm.HS512) //采用什么算法是可以自己选择的，不一定非要采用HS512
                    .serializeToJsonWith(new GsonSerializer<>(new Gson()))
                    .compact();
            // 生成token end
            // 登录成功后，返回token到header里面
            response.setHeader("Access-Control-Expose-Headers",Constant.AUTHORIZATION);
            response.addHeader(Constant.AUTHORIZATION, Constant.PREFIX + token);
            ResponseUtil.add(response, Response.success());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
