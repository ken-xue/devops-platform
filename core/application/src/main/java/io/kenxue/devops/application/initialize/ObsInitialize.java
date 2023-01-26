package io.kenxue.devops.application.initialize;

import com.alibaba.fastjson.JSONObject;
import io.kenxue.devops.acl.obs.ObjectStorageService;
import io.kenxue.devops.domain.domain.sys.Config;
import io.kenxue.devops.domain.repository.sys.ConfigRepository;
import io.kenxue.devops.sharedataboject.system.enums.ConfigEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ObsInitialize implements InitializingBean {

    @Resource
    private ConfigRepository configRepository;
    @Resource
    private ObjectStorageService objectStorageService;

    @Override
    public void afterPropertiesSet() {
        new Thread(()->{
            Config config = configRepository.getByName(ConfigEnum.DEFAULT_OBS.name());
            if (Objects.nonNull(config)){
                try {
                    String cfg = JSONObject.toJSONString(config);
                    log.debug("cfg :{}",cfg);
                    objectStorageService.initialize(cfg);
                } catch (Exception e){
                    log.error("初始化系统缺省OBS配置失败，请及时前往系统配置中进行配置");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
