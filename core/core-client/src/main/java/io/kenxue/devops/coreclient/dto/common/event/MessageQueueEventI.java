package io.kenxue.devops.coreclient.dto.common.event;

public interface MessageQueueEventI extends EventI {
    public String getEventType();
    public String getEventTopic();
}
