package io.kenxue.devops.application.sys.config.command.query;

import io.kenxue.devops.application.sys.config.assembler.Config2DTOAssembler;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigGetQry;
import io.kenxue.devops.domain.repository.sys.ConfigRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Component
public class ConfigGetQryExe {

    @Resource
    private ConfigRepository configRepository;
    @Resource
    private Config2DTOAssembler config2DTOAssembler;

    public SingleResponse<ConfigDTO> execute(ConfigGetQry qry) {
        return SingleResponse.of(config2DTOAssembler.toDTO(configRepository.getById(qry.getId())));
    }

}
