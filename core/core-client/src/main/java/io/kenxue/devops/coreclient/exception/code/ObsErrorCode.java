package io.kenxue.devops.coreclient.exception.code;

public enum ObsErrorCode implements ErrorCode{

    SYSTEM_OBS_NOT_CONFIG(9001,"系统OBS服务未配置，请及时前往系统配置中进行配置");

    public int code;
    public String desc;

    ObsErrorCode(int code, String desc) {
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
