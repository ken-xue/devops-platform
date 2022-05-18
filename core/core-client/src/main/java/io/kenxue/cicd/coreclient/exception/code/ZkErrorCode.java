package io.kenxue.cicd.coreclient.exception.code;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-189:46
 */
public enum ZkErrorCode implements ErrorCode{
    CUSTOM_EXCEPTION(8000,null),
    UNKNOWN_EXCEPTION(8001,"未知异常"),
    CONNECTION_ERROR(8002,"连接失败，请检查网络或zk服务是否正常"),
    ADD_NODE_ERROR(8003,"添加节点失败，节点已存在或其他原因"),
    UPDATE_ERROR(8004, "更新失败")
    ;

    private  Integer errCode;
    private  String errDesc;

    ZkErrorCode(Integer errCode, String errDesc) {
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
