package io.kenxue.devops.coreclient.exception.code;

/**
 * 状态码获取接口
 */
public interface ErrorCode {

    Integer getCode();

    String getDesc();

    ErrorCode setMsg(String msg);
}
