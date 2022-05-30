package io.kenxue.devops.coreclient.exception;
import lombok.experimental.Accessors;
/**
 * 自定义业务异常
 */
@Accessors(chain = true)
public class BizException extends BaseException {

	private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

	public BizException() {
		super(null);
	}

	public BizException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public BizException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public BizException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public BizException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}
}
