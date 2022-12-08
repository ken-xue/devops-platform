package io.kenxue.devops.application.sys.config.command.query;

import io.kenxue.devops.application.sys.config.assembler.Config2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigDTO;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigListQry;
import io.kenxue.devops.domain.domain.sys.Config;
import io.kenxue.devops.domain.repository.sys.ConfigRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Component
public class ConfigListQryExe {
    @Resource
    private ConfigRepository configRepository;
    @Resource
    private Config2DTOAssembler config2DTOAssembler;

    public MultiResponse<ConfigDTO> execute(ConfigListQry qry) {
        List<Config> configDTOList = configRepository.list(qry);
        return MultiResponse.of(config2DTOAssembler.toDTOList(configDTOList));
    }
}
