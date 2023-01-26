package io.kenxue.devops.coreclient.exception.code;

public enum BaseErrorCode implements ErrorCode {

    SUCCESS(2000,"操作成功"),

    BIZ_ERROR(5000, "通用的业务逻辑错误"),

    FUNCTION_NOT_IMPLEMENTED(4000, "功能未实现"),

    SYS_ERROR(8000, "未知的系统错误" );

    private Integer errCode;
    private String errDesc;

    private BaseErrorCode(Integer errCode, String errDesc){
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    
    public Integer getCode() {
        return errCode;
    }

    
    public String getDesc() {
        return errDesc;
    }

    @Override
    public ErrorCode setMsg(String msg) {
        this.errDesc = msg;
        return this;
    }
}

