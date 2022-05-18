package io.kenxue.cicd.coreclient.exception.code;

/**
 * 状态码获取接口
 */
public interface ErrorCode {

    Integer getCode();

    String getDesc();

    /**
     * 设置自定义异常消息
     * @param msg
     * @return
     */
    ErrorCode setMsg(String msg);
}
