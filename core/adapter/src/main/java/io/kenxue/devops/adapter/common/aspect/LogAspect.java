package io.kenxue.devops.adapter.common.aspect;

import com.alibaba.fastjson.JSON;
import io.kenxue.devops.adapter.common.annotation.Log;
import io.kenxue.devops.adapter.util.IPUtil;
import io.kenxue.devops.coreclient.api.sys.LogAppService;
import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.sys.log.LogAddCmd;
import io.kenxue.devops.coreclient.dto.sys.log.LogDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

/**
 * @Author: 麦奇
 * @Date: 21-11-13 上午10:52
 */
@Aspect // 标记为一个切面，让容器读取
@Component
@Slf4j
public class LogAspect {
	@Resource
	private LogAppService logAppService;

	@Pointcut("@annotation(io.kenxue.devops.adapter.common.annotation.Log)")
	public void logPointCut() {}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		Object result = point.proceed();
		long time = System.currentTimeMillis() - beginTime;
		saveActionLog(point, time);
		return result;
	}

	private void saveActionLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
        LogDTO logDTO = new LogDTO();
        logDTO.setOccurTime(new Date());
        Log logAnnotation = method.getAnnotation(Log.class);
		if(Objects.nonNull(logAnnotation))logDTO.setOperation(logAnnotation.value());
		//方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		logDTO.setMethod(className + "." + methodName + "()");
		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = JSON.toJSONString(args);
			logDTO.setParams(params);
		}catch (Exception e){
            log.error("get param error:",e.getMessage());
		}
		//IP地址
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		logDTO.setIp(IPUtil.getIpAddress(request));
		logDTO.setUsername(UserThreadContext.get());
		logDTO.setExecuteTime(time);
		//保存
        LogAddCmd cmd = new LogAddCmd();
        cmd.setLogDTO(logDTO);
        logAppService.add(cmd);
	}
}
