package io.kenxue.cicd.application.pipeline.pipeline.loader;

import io.kenxue.cicd.application.pipeline.pipeline.manager.NodeManager;
import io.kenxue.cicd.sharedataboject.pipeline.node.Node;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NodeLoader implements InitializingBean {

    @Resource
    private NodeManager nodeManager;
    @Resource
    private ApplicationContext applicationContext;

    public void loaderAll(){
        applicationContext.getBeansOfType(Node.class).values().forEach(v->nodeManager.add(v.getName(),v));
    }

    @Override
    public void afterPropertiesSet() {
        loaderAll();
    }
}
