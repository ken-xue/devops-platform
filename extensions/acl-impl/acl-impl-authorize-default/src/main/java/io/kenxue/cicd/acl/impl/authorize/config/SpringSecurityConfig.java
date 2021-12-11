package io.kenxue.cicd.acl.impl.authorize.config;

import io.kenxue.cicd.acl.impl.authorize.AuthorizeService;
import io.kenxue.cicd.acl.impl.authorize.filter.JWTAuthenticationFilter;
import io.kenxue.cicd.acl.impl.authorize.filter.JWTLoginFilter;
import io.kenxue.cicd.acl.impl.authorize.handler.CustomAuthenticationEntryPoint;
import io.kenxue.cicd.acl.impl.authorize.impl.AuthenticationProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager configAuthenticationManager() throws Exception{
        return authenticationManager();
    }
    /**
     * 需要放行的URL
     */
    private static final String[] AUTH_WHITELIST = {
            "/sys/user/add",//注册接口
            "/captcha/get",//注册接口
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/ok"
    };

    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    private AccessDeniedHandler customAccessDeniedHandler;
    @Resource
    private LogoutSuccessHandler customLogoutSuccessHandler;
    @Resource
    private AuthorizeService authorizeService;

    // 设置 HTTP 验证规则
    
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()  // 所有请求需要身份认证
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint("Basic realm=DevAdmin"))
                .accessDeniedHandler(customAccessDeniedHandler) // 自定义访问失败处理器
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager(),authorizeService))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .logout() // 默认注销行为为logout，可以通过下面的方式来修改
                .logoutUrl("/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll();
    }

    // 该方法是登录的时候会进入
    
    public void configure(AuthenticationManagerBuilder auth) {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new AuthenticationProviderImpl(userDetailsService, bCryptPasswordEncoder));
    }
}

