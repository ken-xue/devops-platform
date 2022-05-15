package io.kenxue.cicd.application.middleware.jenkins.pool;

import com.offbytwo.jenkins.JenkinsServer;
import io.kenxue.cicd.application.middleware.jenkins.factory.JenkinsFactory;
import io.kenxue.cicd.coreclient.dto.middleware.jenkins.JenkinsListQry;
import io.kenxue.cicd.domain.domain.middleware.Jenkins;
import io.kenxue.cicd.domain.repository.middleware.jenkins.JenkinsRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Service
public class JenkinsPool {

    private ConcurrentHashMap<String,JenkinsServer> pool = new ConcurrentHashMap<>(2<<4);

    @Resource
    private JenkinsFactory jenkinsFactory;

    @Resource
    private JenkinsRepository jenkinsRepository;

    public JenkinsServer get(){
        Assert.notEmpty(pool,"jenkins pool is empty");
        return pool.values().stream().collect(Collectors.toList()).get(0);
    }

    public JenkinsServer get(String key){
        Assert.notEmpty(pool,"jenkins pool is empty");
        return pool.get(key);
    }

    @PostConstruct
    public void init(){
        List<Jenkins> jenkinsList = jenkinsRepository.list(new JenkinsListQry());
        jenkinsList.forEach(jenkins -> {
            JenkinsServer jenkinsServer = jenkinsFactory.create(jenkins);
            pool.put(jenkins.getUuid(),jenkinsServer);
        });
    }
}
