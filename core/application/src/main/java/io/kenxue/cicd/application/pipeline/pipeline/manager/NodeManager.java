package io.kenxue.cicd.application.pipeline.pipeline.manager;


import io.kenxue.cicd.sharedataboject.pipeline.node.Node;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class NodeManager {

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
}
