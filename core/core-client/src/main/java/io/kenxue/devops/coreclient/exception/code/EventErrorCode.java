package io.kenxue.devops.coreclient.exception.code;

public enum EventErrorCode implements ErrorCode {

    EVENT_ERROR(7000, "事件错误"),
    EVENT_HANDLER_NOT_FOUND_ERROR(7001, "查找不到事件处理器");

    private Integer errCode;
    private String errDesc;

    private EventErrorCode(Integer errCode, String errDesc){
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
