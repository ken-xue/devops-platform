package io.kenxue.devops.application.sys.config.command;

import io.kenxue.devops.application.sys.config.assembler.Config2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.sys.ConfigRepository;
import io.kenxue.devops.domain.domain.sys.Config;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigAddCmd;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Component
public class ConfigAddCmdExe {

    @Resource
    private ConfigRepository configRepository;
    @Resource
    private Config2DTOAssembler config2DTOAssembler;

    public Response execute(ConfigAddCmd cmd) {
        Config config = config2DTOAssembler.toDomain(cmd.getConfigDTO());
        config.create();
        configRepository.create(config);
        return Response.success();
    }
}
