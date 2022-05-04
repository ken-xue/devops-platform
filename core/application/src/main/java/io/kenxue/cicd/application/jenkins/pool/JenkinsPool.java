package io.kenxue.cicd.application.jenkins.pool;

import com.offbytwo.jenkins.JenkinsServer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class JenkinsPool {

    private ConcurrentHashMap<String,JenkinsServer> pool = new ConcurrentHashMap<>(2<<4);

    public JenkinsServer get(String key){
        Assert.notEmpty(pool,"jenkins pool is empty");
        return pool.get(key);
    }

    @PostConstruct
    public void init(){

    }
}
