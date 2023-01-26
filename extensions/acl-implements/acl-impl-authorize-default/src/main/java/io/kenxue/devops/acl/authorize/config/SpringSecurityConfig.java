package io.kenxue.devops.acl.authorize.config;

import io.kenxue.devops.acl.authorize.AuthorizeService;
import io.kenxue.devops.acl.authorize.filter.JWTAuthenticationFilter;
import io.kenxue.devops.acl.authorize.filter.JWTLoginFilter;
import io.kenxue.devops.acl.authorize.handler.CustomAuthenticationEntryPoint;
import io.kenxue.devops.acl.authorize.handler.CustomLogoutSuccessHandler;
import io.kenxue.devops.acl.authorize.impl.AuthenticationProviderImpl;
import io.kenxue.devops.acl.cache.CacheService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    private AccessDeniedHandler customAccessDeniedHandler;
    @Resource
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    @Resource
    private AuthorizeService authorizeService;
    @Resource
    @Qualifier("authCachedImpl")
    private CacheService cacheService;

    @Resource
    private AuthenticationConfiguration auth;

    CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors().configurationSource(configurationSource()).and()
                // 基于 token，不需要 csrf
                .csrf().disable()
                // 基于 token，不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()// 放开权限
                // 下面开始设置权限
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/ok",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/*.html",
                                "/favicon.ico",
                                "/*/*.html",
                                "/*/*.css",
                                "/*/*.js",
                                "/swagger-resources/**",
                                "/v3/api-docs/**",
                                "/ws/**",//开放websocket接口
                                "/sys/user/add",//注册接口
                                "/captcha/get"//获取验证码接口
                                ).anonymous()
                        .anyRequest().authenticated()
                )
                // 设置 jwtAuthError 处理认证失败、鉴权失败
                .exceptionHandling()
                .authenticationEntryPoint((new CustomAuthenticationEntryPoint("Basic realm=devops-platform")))
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .authenticationProvider(new AuthenticationProviderImpl(userDetailsService, bCryptPasswordEncoder))

                // 添加 JWT 过滤器
                .addFilter(new JWTLoginFilter(auth.getAuthenticationManager(),authorizeService,cacheService))
                .addFilter(new JWTAuthenticationFilter(auth.getAuthenticationManager(),cacheService))
                .logout() // 默认注销行为为logout，可以通过下面的方式来修改
                .logoutUrl("/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                .build();
    }
}

