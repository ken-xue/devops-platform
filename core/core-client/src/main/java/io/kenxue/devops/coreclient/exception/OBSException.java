package io.kenxue.devops.coreclient.exception;

import io.kenxue.devops.coreclient.exception.code.OBSErrorCode;

public class OBSException extends BaseException {

    private OBSErrorCode errorCode;

    public OBSException(String message) {
        super(message);
    }

    public OBSException(OBSErrorCode errorCode) {
        super(errorCode.getDesc());
        setCode(errorCode);
    }

    public OBSException(String message, Throwable e) {
        super(message, e);
    }
}
