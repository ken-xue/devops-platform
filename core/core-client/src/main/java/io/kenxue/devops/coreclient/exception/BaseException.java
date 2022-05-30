package io.kenxue.devops.coreclient.exception;

import io.kenxue.devops.coreclient.exception.code.ErrorCode;

public abstract class BaseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private ErrorCode code;

    public BaseException(String message){
        super(message);
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

}
