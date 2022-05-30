package io.kenxue.devops.application.common.event;


import io.kenxue.devops.coreclient.dto.common.event.EventI;
import io.kenxue.devops.coreclient.dto.common.response.Response;

public interface EventBusI {
    /**
     * 发送事件
     * @param event
     * @return
     */
    Response publish(EventI event);
    /**
     * 发送事件
     * @param event
     * @return Response
     */
    void publishAll(EventI event);
    /**
     * 异步发送事件
     * @param event
     */
    void asyncPublish(EventI event);
}

