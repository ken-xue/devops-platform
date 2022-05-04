package io.kenxue.cicd.application.application.appconfig.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.application.applicationconfig.ApplicationConfigDTO;
import io.kenxue.cicd.domain.domain.application.ApplicationConfig;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationConfig2DTOAssembler implements Assembler<ApplicationConfigDTO, ApplicationConfig> {

    @Override
    public ApplicationConfigDTO toDTO(ApplicationConfig applicationConfig) {
        return ApplicationConfig2DTOMapStruct.INSTANCE.toDTO(applicationConfig);
    }

    @Override
    public ApplicationConfig toDomain(ApplicationConfigDTO applicationConfigDTO) {
        return ApplicationConfig2DTOMapStruct.INSTANCE.toDomain(applicationConfigDTO);
    }

    @Override
    public List<ApplicationConfigDTO> toDTOList(List<ApplicationConfig> applicationConfigList) {
        return ApplicationConfig2DTOMapStruct.INSTANCE.toDTOList(applicationConfigList);
    }

    @Override
    public List<ApplicationConfig> toDomainList(List<ApplicationConfigDTO> applicationConfigDTOList) {
        return ApplicationConfig2DTOMapStruct.INSTANCE.toDomainList(applicationConfigDTOList);
    }

    @Override
    public Page<ApplicationConfigDTO> toDTOPage(Page<ApplicationConfig> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), ApplicationConfig2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
