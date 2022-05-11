package io.kenxue.cicd.application.pipeline.pipeline.manager;

import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class JschSessionManager {

    private ConcurrentHashMap<String, Session> cached = new ConcurrentHashMap<>(2<<4);

    public void add(String key,Session session){
        cached.put(key,session);
    }

    public Session get(String key){
        return cached.get(key);
    }

    public Session remove(String key){
        return cached.remove(key);
    }
}
