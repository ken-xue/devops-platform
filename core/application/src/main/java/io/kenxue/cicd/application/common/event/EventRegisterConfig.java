package io.kenxue.cicd.application.common.event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Component
public class EventRegisterConfig {

    @Resource
    private EventRegister eventRegister;

    @PostConstruct
    public void register(){
        ApplicationContext applicationContext =  ApplicationContextHelper.getApplicationContext();
        Map<String, Object> eventHandlerBeans = applicationContext.getBeansWithAnnotation(EventHandler.class);
        eventHandlerBeans.values().forEach(
                eventHandler -> eventRegister.doRegistration((EventHandlerI) eventHandler)
        );
    }
}
