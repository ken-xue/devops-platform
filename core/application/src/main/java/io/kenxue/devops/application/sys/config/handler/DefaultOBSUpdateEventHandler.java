package io.kenxue.devops.application.sys.config.handler;

import com.alibaba.fastjson.JSONObject;
import io.kenxue.devops.acl.obs.ObjectStorageService;
import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigDTO;
import io.kenxue.devops.coreclient.dto.sys.config.event.DefaultOBSUpdateEvent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统OBS实例更换
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Slf4j
@EventHandler
public class DefaultOBSUpdateEventHandler implements EventHandlerI<Response, DefaultOBSUpdateEvent> {

    @Resource
    private ObjectStorageService objectStorageService;

    public Response execute(DefaultOBSUpdateEvent event) {
        ConfigDTO configDTO = event.getConfigDTO();
        String s = JSONObject.toJSONString(configDTO);
        objectStorageService.initialize(s);
        return Response.success();
    }
}
