package io.kenxue.cicd.adapter.common.exception;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.exception.AuthException;
import io.kenxue.cicd.coreclient.exception.BizException;
import io.kenxue.cicd.coreclient.exception.code.AuthErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
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

	@ExceptionHandler(Exception.class)
	public Response handleException(Exception e){
		log.error(e.getMessage(), e);
		return Response.error(500,e.getMessage());
	}
}
