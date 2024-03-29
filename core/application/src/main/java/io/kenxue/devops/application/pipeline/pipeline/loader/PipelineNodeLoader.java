package io.kenxue.devops.application.pipeline.pipeline.loader;

import io.kenxue.devops.application.common.loader.Loader;
import io.kenxue.devops.application.pipeline.pipeline.node.builder.JavaBuildNode;
import io.kenxue.devops.sharedataboject.pipeline.node.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        List<Node> nodeList = applicationContext.getBeansOfType(Node.class).values().stream().collect(Collectors.toList());
        log.info("all node : {}",nodeList);
        return nodeList;
    }

    @Override
    public List<Node> loadAll(List<String> keys) {
        return null;
    }
}
