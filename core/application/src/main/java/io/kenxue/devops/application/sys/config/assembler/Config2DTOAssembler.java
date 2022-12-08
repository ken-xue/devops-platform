package io.kenxue.devops.application.sys.config.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigDTO;
import io.kenxue.devops.domain.domain.sys.Config;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Component
public class Config2DTOAssembler implements Assembler<ConfigDTO, Config> {

    @Override
    public ConfigDTO toDTO(Config config) {
        return Config2DTOMapStruct.INSTANCE.toDTO(config);
    }

    @Override
    public Config toDomain(ConfigDTO configDTO) {
        return Config2DTOMapStruct.INSTANCE.toDomain(configDTO);
    }

    @Override
    public List<ConfigDTO> toDTOList(List<Config> configList) {
        return Config2DTOMapStruct.INSTANCE.toDTOList(configList);
    }

    @Override
    public List<Config> toDomainList(List<ConfigDTO> configDTOList) {
        return Config2DTOMapStruct.INSTANCE.toDomainList(configDTOList);
    }

    @Override
    public Page<ConfigDTO> toDTOPage(Page<Config> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), Config2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
