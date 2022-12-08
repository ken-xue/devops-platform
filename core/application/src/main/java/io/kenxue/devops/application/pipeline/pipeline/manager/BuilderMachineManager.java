package io.kenxue.devops.application.pipeline.pipeline.manager;

import io.kenxue.devops.application.pipeline.pipeline.loader.BuilderMachineLoader;
import io.kenxue.devops.domain.domain.machine.MachineInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author 麦奇
 */
@Service
public class BuilderMachineManager implements InitializingBean {

    private ConcurrentHashMap<String, MachineInfo> cached = new ConcurrentHashMap<>(2<<4);

    @Resource
    private BuilderMachineLoader builderMachineLoader;

    public void add(String key,MachineInfo machineInfo){
        cached.put(key,machineInfo);
    }

    public MachineInfo get(String key){
        return cached.get(key);
    }

    public MachineInfo getRandom(){
        List<MachineInfo> machineInfoList = cached.values().stream().collect(Collectors.toList());
        return machineInfoList.get((int) (Math.random()*machineInfoList.size()));
    }

    public MachineInfo remove(String key){
        return cached.remove(key);
    }

    @Override
    public void afterPropertiesSet() {
        List<MachineInfo> machineInfos = builderMachineLoader.loadAll();
        machineInfos.forEach(m-> add(m.getIp(),m));
    }
}
