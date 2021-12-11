package io.kenxue.cicd.coreclient.dto.common.event;

public interface MessageQueueEventI extends EventI {
    public String getEventType();
    public String getEventTopic();
}
