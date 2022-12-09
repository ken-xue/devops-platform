package io.kenxue.devops.application.sys.config.command;

import io.kenxue.devops.application.common.event.EventBusI;
import io.kenxue.devops.application.sys.config.assembler.Config2DTOAssembler;
import io.kenxue.devops.coreclient.dto.sys.config.event.DefaultOBSUpdateEvent;
import io.kenxue.devops.domain.domain.sys.Config;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigUpdateCmd;
import io.kenxue.devops.domain.repository.sys.ConfigRepository;
import io.kenxue.devops.sharedataboject.system.enums.ConfigEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Component
public class ConfigUpdateCmdExe {

    @Resource
    private ConfigRepository configRepository;
    @Resource
    private Config2DTOAssembler config2DTOAssembler;
    @Resource
    private EventBusI eventBusI;

    public Response execute(ConfigUpdateCmd cmd) {
        Config config = config2DTOAssembler.toDomain(cmd.getConfigDTO());
        //更换系统OBS服务实例
        if (ConfigEnum.DEFAULT_OBS.name().equals(config.getName())){
            eventBusI.publish(new DefaultOBSUpdateEvent(cmd.getConfigDTO()));
        }
        configRepository.update(config);
        return Response.success();
    }
}