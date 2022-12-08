package io.kenxue.devops.application.common.event;


import io.kenxue.devops.coreclient.dto.common.event.EventI;
import io.kenxue.devops.coreclient.exception.EventException;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.lang.reflect.Method;

@Component
public class EventRegister{

    @Resource
    private EventHub eventHub;

    private Class<? extends EventI> getEventFromExecutor(Class<?> eventExecutorClz) {
        Method[] methods = eventExecutorClz.getDeclaredMethods();
        for (Method method : methods) {
            if (isExecuteMethod(method)){
                return checkAndGetEventParamType(method);
            }
        }
        throw new EventException("Event param in " + eventExecutorClz + " "  + "() is not detected");
    }

    public void doRegistration(EventHandlerI eventHandler){
        Class<? extends EventI> eventClz = getEventFromExecutor(eventHandler.getClass());
        eventHub.register(eventClz, eventHandler);
    }

    private boolean isExecuteMethod(Method method){
        return "execute".equals(method.getName()) && !method.isBridge();
    }

    private Class checkAndGetEventParamType(Method method){
        Class<?>[] exeParams = method.getParameterTypes();
        if (exeParams.length == 0){
            throw new EventException("Execute method in "+method.getDeclaringClass()+" should at least have one parameter");
        }
        if(!EventI.class.isAssignableFrom(exeParams[0]) ){
            throw new EventException("Execute method in "+method.getDeclaringClass()+" should be the subClass of Event");
        }
        return exeParams[0];
    }
}

