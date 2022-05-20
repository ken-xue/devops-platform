package io.kenxue.cicd.application.pipeline.pipeline.handler;

import com.alibaba.fastjson.JSON;
import io.kenxue.cicd.application.common.event.EventHandler;
import io.kenxue.cicd.application.common.event.EventHandlerI;
import io.kenxue.cicd.application.common.websocket.WebSocketService;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.PushNodeExecuteStatusDTO;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.event.PipelineNodeRefreshEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

/**
 * 流水线节点更新事件处理器
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@EventHandler
public class PipelineRefreshEventHandler implements EventHandlerI<Response, PipelineNodeRefreshEvent> {

    @Resource
    @Qualifier("pipelineExecuteSocketServiceImpl")
    private WebSocketService webSocketService;

    public Response execute(PipelineNodeRefreshEvent event) {
        byte[] bytes = JSON.toJSONString(PushNodeExecuteStatusDTO.builder().edges(event.getEdges()).nodes(event.getData()).build()).getBytes();
        webSocketService.sendMessage(event.getUuid(),bytes);
        //加队列执行
        return Response.success();
    }
}
