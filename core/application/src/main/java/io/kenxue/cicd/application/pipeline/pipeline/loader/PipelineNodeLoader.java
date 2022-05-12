package io.kenxue.cicd.application.pipeline.pipeline.loader;

import io.kenxue.cicd.application.common.loader.Loader;
import io.kenxue.cicd.sharedataboject.pipeline.node.Node;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PipelineNodeLoader implements Loader<String,Node> {

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public Node load() {
        return null;
    }

    @Override
    public Node load(String key) {
        return null;
    }

    @Override
    public List<Node> loadAll() {
        return applicationContext.getBeansOfType(Node.class).values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Node> loadAll(List<String> keys) {
        return null;
    }
}
