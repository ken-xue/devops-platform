package io.kenxue.devops.coreclient.exception.code;

public enum OBSErrorCode implements ErrorCode{

    SYSTEM_OBS_NOT_CONFIG(10001,"系统OBS服务未配置，请前往系统配置中进行配置"),
    SYSTEM_OBS_INIT_ERROR(10002,"系统OBS服务初始化失败");

    public int code;
    public String desc;

    OBSErrorCode(int code, String desc) {
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
