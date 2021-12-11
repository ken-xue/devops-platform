package io.kenxue.cicd.coreclient.exception.code;

public enum AuthErrorCode implements ErrorCode{

    SUCCESS(2000,"操作成功"),
    NEED_LOGIN(4010,"请先登录"),
    EXPIRED_TOKEN(4011,"Token已过期"),
    ERROR_FORMAT_TOKEN(4012,"Token格式错误"),
    SIGNATURE_FAIL(4013,"签名失败"),
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
}
