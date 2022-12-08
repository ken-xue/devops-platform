package io.kenxue.devops.application.sys.config.command.query;

import io.kenxue.devops.application.sys.config.assembler.Config2DTOAssembler;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigDTO;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.sys.ConfigRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.sys.Config;

import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Component
public class ConfigPageQryExe {

    @Resource
    private ConfigRepository configRepository;
    @Resource
    private Config2DTOAssembler config2DTOAssembler;

    public PageResponse<ConfigDTO> execute(ConfigPageQry qry) {
        Page<Config> page = configRepository.page(qry);
        return PageResponse.of(config2DTOAssembler.toDTOPage(page));
    }
}
