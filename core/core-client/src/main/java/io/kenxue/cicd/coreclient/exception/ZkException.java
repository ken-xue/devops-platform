package io.kenxue.cicd.coreclient.exception;

import io.kenxue.cicd.coreclient.exception.code.ZkErrorCode;
import lombok.*;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-189:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ZkException extends RuntimeException{

    private ZkErrorCode zkErrorCode;

    public ZkException(ZkErrorCode zkErrorCode){
        this.zkErrorCode = zkErrorCode;
    }

    public ZkException(ZkErrorCode zkErrorCode, String message){
        this.zkErrorCode = zkErrorCode;
        this.zkErrorCode.setMsg(message);
    }

}
