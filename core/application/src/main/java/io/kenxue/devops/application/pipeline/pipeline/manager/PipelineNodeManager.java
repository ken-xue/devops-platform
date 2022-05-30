package io.kenxue.devops.application.pipeline.pipeline.manager;


import io.kenxue.devops.application.pipeline.pipeline.loader.PipelineNodeLoader;
import io.kenxue.devops.sharedataboject.pipeline.node.Node;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 麦奇
 */
@Service
public class PipelineNodeManager implements InitializingBean {

    @Resource
    private PipelineNodeLoader pipelineNodeLoader;

    private ConcurrentHashMap<String, Node> cached = new ConcurrentHashMap<>(2<<4);

    public void add(String key,Node node){
        cached.put(key,node);
    }

    public Node get(String key){
        return cached.get(key);
    }

    public Node remove(String key){
        return cached.remove(key);
    }

    @Override
    public void afterPropertiesSet() {
        pipelineNodeLoader.loadAll().forEach(node -> add(node.getName(),node));
    }
}
