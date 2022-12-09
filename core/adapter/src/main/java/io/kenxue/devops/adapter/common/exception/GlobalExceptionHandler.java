package io.kenxue.devops.adapter.common.exception;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.exception.BizException;
import io.kenxue.devops.coreclient.exception.OBSException;
import io.kenxue.devops.coreclient.exception.ZkException;
import io.kenxue.devops.coreclient.exception.code.AuthErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BizException.class)
	public Response handleBizException(BizException e){
		Response response = Response.error(e.getCode().getCode(),e.getMessage());
		return response;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public Response handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return Response.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Response handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return Response.error(5000,"该数据已存在");
	}

	@ExceptionHandler({AuthenticationException.class,AccessDeniedException.class})
	public Response handleAuthorizationException(AuthenticationException e){
		log.error(e.getMessage(), e);
		return Response.of(AuthErrorCode.NOT_HAVE_PERMISSION);
	}

	@ExceptionHandler(BindException.class)
	public Response exceptionHandler(BindException e) {
		String failMsg = e.getBindingResult().getFieldError().getDefaultMessage();
		return Response.error(failMsg);
	}

	@ExceptionHandler(SQLException.class)
	public Response sqlExceptionHandler(SQLException e) {
		log.error(e.getMessage(), e);
		return Response.error(500,e.getMessage());
	}

	@ExceptionHandler(OBSException.class)
	public Response handleOBSException(OBSException e){
		log.error(e.getMessage(), e);
		return Response.error(500,e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public Response handleException(Exception e){
		log.error(e.getMessage(), e);
		return Response.error(500,e.getMessage());
	}

	@ExceptionHandler(ZkException.class)
	public Response zkException(ZkException e){
		return Response.error(e.getZkErrorCode().getCode(),e.getZkErrorCode().getDesc());
	}
}
