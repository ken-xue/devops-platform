package io.kenxue.devops.application.sys.config.handler;

import io.kenxue.devops.application.common.event.EventHandler;
import io.kenxue.devops.application.common.event.EventHandlerI;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.config.event.DefaultOBSUpdateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统OBS实例更换
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Slf4j
@EventHandler
public class DefaultOBSUpdateEventHandler implements EventHandlerI<Response, DefaultOBSUpdateEvent> {
    
    public Response execute(DefaultOBSUpdateEvent event) {

        return Response.success();
    }

//    public MinioClient getMiniClient(){
//        MinioClient minioClient = MinioClient.builder()
//                .endpoint(url)
//                .credentials(accessKey, secretKey)
//                .build();
//        return minioClient;
//    }
}
