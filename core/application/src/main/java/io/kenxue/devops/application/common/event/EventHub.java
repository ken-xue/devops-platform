package io.kenxue.devops.application.common.event;


import io.kenxue.devops.coreclient.dto.common.event.EventI;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件控制中枢
 */
@Component
public class EventHub {

    private HashMap<Class, List<EventHandlerI>> eventRepository = new HashMap<>();

    private Map<Class, Class> responseRepository = new HashMap<>();

    public HashMap<Class, List<EventHandlerI>> getEventRepository() {
        return eventRepository;
    }

    public void setEventRepository(HashMap<Class, List<EventHandlerI>> eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Map<Class, Class> getResponseRepository() {
        return responseRepository;
    }

    @SneakyThrows
    public List<EventHandlerI> getEventHandler(Class eventClass) {
        List<EventHandlerI> eventHandlerIList = findHandler(eventClass);
        if (eventHandlerIList == null || eventHandlerIList.size() == 0) {
            throw new Exception(eventClass + " is not registered in eventHub, please register first");
        }
        return eventHandlerIList;
    }

    /**
     * 注册事件
     * @param eventClz
     * @param executor
     */
    public void register(Class<? extends EventI> eventClz, EventHandlerI executor){
        List<EventHandlerI> eventHandlerIS = eventRepository.get(eventClz);
        if(eventHandlerIS == null){
            eventHandlerIS = new ArrayList<>();
            eventRepository.put(eventClz, eventHandlerIS);
        }
        eventHandlerIS.add(executor);

    }

    private List<EventHandlerI> findHandler(Class<? extends EventI> eventClass){
        List<EventHandlerI> eventHandlerIIList = null;
        Class cls = eventClass;
        eventHandlerIIList = eventRepository.get(cls);
        return eventHandlerIIList;
    }

}

