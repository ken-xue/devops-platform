package io.kenxue.cicd.coreclient.exception;

import io.kenxue.cicd.coreclient.exception.code.AuthErrorCode;

public class AuthException extends BaseException {

    private AuthErrorCode errorCode;

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable e) {
        super(message, e);
    }
}
