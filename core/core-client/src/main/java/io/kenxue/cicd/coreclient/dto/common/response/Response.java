package io.kenxue.cicd.coreclient.dto.common.response;


import io.kenxue.cicd.coreclient.dto.common.command.DTO;
import io.kenxue.cicd.coreclient.exception.code.ErrorCode;

public class Response extends DTO {

    private static final long serialVersionUID = 1L;

    private Integer code = 2000;//默认成功

    private String message = "执行成功";//操作成功

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public String toString() {
        return "Response [code=" + code + ", message=" + message + "]";
    }

    public static Response success() {
        Response response = new Response();
        return response;
    }

    public static Response success(String message) {
        Response response = new Response();
        response.setMessage(message);
        return response;
    }

    public static Response error(Integer code, String message) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static Response of(ErrorCode errorCode) {
        Response response = new Response();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getDesc());
        return response;
    }

    public static Response error(String message) {
        Response response = new Response();
        response.setCode(500);
        response.setMessage(message);
        return response;
    }

}
