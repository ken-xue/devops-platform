package io.kenxue.cicd.application.common.event;

import io.kenxue.cicd.coreclient.exception.EventException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationContextHelper implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return  ApplicationContextHelper.applicationContext;
    }

    /**
     * 获取bean
     * @param targetClz
     * @param <T>
     * @return
     */
    public static<T> T getBean(Class<T> targetClz){
        T beanInstance = null;
        try {
            beanInstance = (T) applicationContext.getBean(targetClz);
        }catch (Exception e){
            log.info("get bean error:{}",e.getMessage());
        }
        if(beanInstance == null){
            String simpleName = targetClz.getSimpleName();
            simpleName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
            beanInstance = (T) applicationContext.getBean(simpleName);
        }
        if(beanInstance == null){
            new EventException("Component " + targetClz + " can not be found in Spring Container");
        }
        return beanInstance;
    }

    /**
     *
     * @param clazz
     * @return
     */
    public static Object getBean(String clazz){
        return ApplicationContextHelper.applicationContext.getBean(clazz);
    }
}
