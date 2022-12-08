package io.kenxue.devops.application.sys.config.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.sys.ConfigRepository;
import io.kenxue.devops.domain.domain.sys.Config;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigDeleteCmd;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Component
public class ConfigDeleteCmdExe {
    @Resource
    private ConfigRepository configRepository;

    public Response execute(ConfigDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            Config config =configRepository.getById(id);
            config.deleted(UserThreadContext.get());
            configRepository.update(config);
        }
        return Response.success();
    }
}
