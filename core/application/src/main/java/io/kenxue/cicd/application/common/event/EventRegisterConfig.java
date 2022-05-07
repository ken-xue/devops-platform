package io.kenxue.cicd.application.common.event;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class EventRegisterConfig implements InitializingBean {

    @Resource
    private EventRegister eventRegister;
    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        Map<String, Object> eventHandlerBeans = applicationContext.getBeansWithAnnotation(EventHandler.class);
        eventHandlerBeans.values().forEach(
                eventHandler -> eventRegister.doRegistration((EventHandlerI) eventHandler)
        );
    }
}
