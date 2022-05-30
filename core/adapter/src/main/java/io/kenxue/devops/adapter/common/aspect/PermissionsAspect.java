package io.kenxue.devops.adapter.common.aspect;

import io.kenxue.devops.acl.authorize.AuthorizeService;
import io.kenxue.devops.adapter.common.annotation.Permissions;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: 麦奇
 * @Date: 21-11-13 上午10:52
 */
@Aspect // 标记为一个切面，让容器读取
@Component
@Slf4j
public class PermissionsAspect {

    @Resource
    private AuthorizeService authorizeService;

    // @annotation的值为自定义注解类全限定名
    @Pointcut("@annotation(io.kenxue.devops.adapter.common.annotation.Permissions)")
    public void permissionsPointCut() {}

    @Before("permissionsPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        this.handleRequestPermissions(joinPoint);
    }

    private void handleRequestPermissions(final JoinPoint joinPoint) {
        // 获取注解
        Permissions request= this.getAnnotationLog(joinPoint);
        // 获取注解值（权限名）
        String value = request.value(); // value="home:menu:list"
        List<String> userPermissions = getUserPermissions();
        // 权限业务逻辑处理，例如：判断用户拥有的权限里是否包含注解value权限标识
        if(!userPermissions.contains(value)){
            log.error("need permission:{}",value);
            throw new AuthenticationServiceException(value);
        }
    }

    private List<String> getUserPermissions() {

        return authorizeService.getPermissions();

    }

    private Permissions getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (null != method) {
            // 返回自定义的注解
            return method.getAnnotation(Permissions.class);
        }
        return null;
    }

}

