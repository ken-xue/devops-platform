package io.kenxue.devops.acl.impl.incident;

import io.kenxue.devops.acl.event.Consumer;
import io.kenxue.devops.application.common.event.EventBus;
import io.kenxue.devops.coreclient.dto.common.event.EventI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class EventConsumerImpl implements Consumer {

    @Resource
    private EventBus eventBus;

    @Override
    public void consumer() {
        //1.pull event
        //TODO
        List<EventI> eventIList = new LinkedList<>();
        //2.execute event
        eventIList.forEach(e->{
            eventBus.publishAll(e);
        });
    }
}
