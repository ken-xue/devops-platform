package io.kenxue.devops.coreclient.exception.code;

public enum AuthErrorCode implements ErrorCode{

    NEED_LOGIN(4010,"请先登录"),
    EXPIRED_TOKEN(4011,"Token已过期"),
    ERROR_FORMAT_TOKEN(4012,"Token格式错误"),
    SIGNATURE_FAIL(4013,"签名失败"),
    NOT_HAVE_PERMISSION(403,"没有该操作权限"),
    ILLEGAL_ARGUMENT(4014,"非法参数异常");

    public int code;
    public String desc;

    AuthErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public ErrorCode setMsg(String msg) {
        this.desc = msg;
        return this;
    }
}
