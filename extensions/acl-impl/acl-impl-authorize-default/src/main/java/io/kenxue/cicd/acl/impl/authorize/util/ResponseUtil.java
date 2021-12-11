package io.kenxue.cicd.acl.impl.authorize.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.kenxue.cicd.coreclient.dto.common.response.Response;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 麦奇
 * @Date: 21-11-13 上午12:00
 */
public class ResponseUtil {
    public static void add(HttpServletResponse response, Response message){
        try {
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(JSONObject.toJSONString(message, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat));
            response.getWriter().flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
