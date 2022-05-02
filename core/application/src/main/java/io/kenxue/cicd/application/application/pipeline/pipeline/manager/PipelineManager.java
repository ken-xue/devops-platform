package io.kenxue.cicd.application.application.pipeline.pipeline.manager;

import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class PipelineManager {

    private ConcurrentHashMap<String, ApplicationPipeline> cache = new ConcurrentHashMap<>(2<<4);

    public ApplicationPipeline add(ApplicationPipeline pipeline){
        return cache.put(pipeline.getUuid(),pipeline);
    }

    public boolean remove(ApplicationPipeline pipeline){
        return cache.remove(pipeline.getPipelineName(),pipeline);
    }

    public ApplicationPipeline get(String uuid) {
        return cache.get(uuid);
    }
}
