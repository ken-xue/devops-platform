package io.kenxue.cicd.application.common.event;

import io.kenxue.cicd.coreclient.dto.common.event.EventI;
import io.kenxue.cicd.coreclient.dto.common.response.Response;

import java.util.concurrent.ExecutorService;

/**
 * 事件处理器顶层接口
 * @param <R>
 * @param <E>
 */
public interface EventHandlerI<R extends Response, E extends EventI> {

    default public ExecutorService getExecutor(){
        return null;
    }

    public R execute(E e);
}
