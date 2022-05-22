package io.kenxue.cicd.application.common.event;

import io.kenxue.cicd.coreclient.exception.BaseException;
import io.kenxue.cicd.coreclient.exception.EventException;
import io.kenxue.cicd.coreclient.exception.code.BaseErrorCode;
import io.kenxue.cicd.coreclient.dto.common.event.EventI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.exception.code.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EventBus implements EventBusI {
    /**
     * 默认线程池
     */
    ExecutorService defaultExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 1,
            10L,TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000));

    @Resource
    private EventHub eventHub;


    public Response publish(EventI eventI) {
        Response response = null;
        EventHandlerI eventHandlerI = null;
        try {
            eventHandlerI = eventHub.getEventHandler(eventI.getClass()).get(0);
            response = eventHandlerI.execute(eventI);
        }catch (Exception exception) {
            response = handleException(eventHandlerI, response, exception);
        }
        return response;
    }

    
    public void publishAll(EventI eventI){
        eventHub.getEventHandler(eventI.getClass()).stream().map(p->{
            Response response = null;
            try {
                response = p.execute(eventI);
            }catch (Exception exception) {
                response = handleException(p, response, exception);
            }
            return response;
        }).collect(Collectors.toList());
    }

    
    public void asyncPublish(EventI eventI) {
        eventHub.getEventHandler(eventI.getClass()).parallelStream().map(p->{
            Response response = null;
            try {
                if(null != p.getExecutor()){
                    p.getExecutor().submit(()->p.execute(eventI));
                }else{
                    defaultExecutor.submit(()->p.execute(eventI));
                }
            }catch (Exception exception) {
                response = handleException(p, response, exception);
            }
            return response;
        }).collect(Collectors.toList());
    }

    private Response handleException(EventHandlerI handler, Response response, Exception exception) {
        log.error(exception.getMessage(), exception);
        Class responseClz = eventHub.getResponseRepository().get(handler.getClass());
        try {
            response = (Response) responseClz.newInstance();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new EventException(e.getMessage());
        }
        if (exception instanceof BaseException) {
            ErrorCode code = ((BaseException) exception).getCode();
            response.setCode(code.getCode());
        }
        else {
            response.setCode(BaseErrorCode.SYS_ERROR.getCode());
        }
        response.setMessage(exception.getMessage());
        return response;
    }
}

