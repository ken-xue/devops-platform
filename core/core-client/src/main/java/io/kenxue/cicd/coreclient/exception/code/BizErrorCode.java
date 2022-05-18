package io.kenxue.cicd.coreclient.exception.code;

public enum BizErrorCode implements ErrorCode {

    BIZ_ERROR_CODE(5231, "业务异常");

    private Integer errCode;
    private String errDesc;

    private BizErrorCode(Integer errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    @Override
    public Integer getCode() {
        return errCode;
    }

    @Override
    public String getDesc() {
        return errDesc;
    }

    @Override
    public ErrorCode setMsg(String msg) {
        this.errDesc = msg;
        return this;
    }
}
