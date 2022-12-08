package io.kenxue.devops.application.middleware.jenkins.pool;

import com.offbytwo.jenkins.JenkinsServer;
import io.kenxue.devops.application.middleware.jenkins.factory.JenkinsFactory;
import io.kenxue.devops.coreclient.dto.middleware.jenkins.JenkinsListQry;
import io.kenxue.devops.domain.domain.middleware.Jenkins;
import io.kenxue.devops.domain.repository.middleware.jenkins.JenkinsRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Service
public class JenkinsPool implements InitializingBean {

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

    @Override
    public void afterPropertiesSet() {
//        List<Jenkins> jenkinsList = jenkinsRepository.list(new JenkinsListQry());
//        jenkinsList.forEach(jenkins -> {
//            JenkinsServer jenkinsServer = jenkinsFactory.create(jenkins);
//            pool.put(jenkins.getUuid(),jenkinsServer);
//        });
    }
}
