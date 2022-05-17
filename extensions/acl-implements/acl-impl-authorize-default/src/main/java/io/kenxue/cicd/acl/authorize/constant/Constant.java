package io.kenxue.cicd.acl.authorize.constant;

public class Constant {
    public static final String SIGNING_KEY = "spring-security-@Jwt!&Secret^#";
    public static final String PREFIX = "Bearer ";
    public static final String AUTHORIZATION = "authorization";
    public static final String REFRESH_TOKEN = "refreshToken";
    /**
     * 验证码过期时间
     */
    public static final Integer captchaExpireTime = 5;
}
