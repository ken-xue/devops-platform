package io.kenxue.devops.coreclient.exception.code;

public enum SSHErrorCode implements ErrorCode{

    CONNECTION_ERROR(9000, "连接失败"),
    ADD_SECRET_KEY_ERROR(9001, "添加秘钥失败");

    private Integer errCode;
    private String errDesc;

    SSHErrorCode(Integer errCode, String errDesc){
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
