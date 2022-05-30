package io.kenxue.devops.application.application.info.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.ApplicationInfoDTO;
import io.kenxue.devops.domain.domain.application.ApplicationInfo;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationInfo2DTOAssembler implements Assembler<ApplicationInfoDTO, ApplicationInfo> {

    @Override
    public ApplicationInfoDTO toDTO(ApplicationInfo applicationInfo) {
        return ApplicationInfo2DTOMapStruct.INSTANCE.toDTO(applicationInfo);
    }

    @Override
    public ApplicationInfo toDomain(ApplicationInfoDTO applicationInfoDTO) {
        return ApplicationInfo2DTOMapStruct.INSTANCE.toDomain(applicationInfoDTO);
    }

    @Override
    public List<ApplicationInfoDTO> toDTOList(List<ApplicationInfo> applicationInfoList) {
        return ApplicationInfo2DTOMapStruct.INSTANCE.toDTOList(applicationInfoList);
    }

    @Override
    public List<ApplicationInfo> toDomainList(List<ApplicationInfoDTO> applicationInfoDTOList) {
        return ApplicationInfo2DTOMapStruct.INSTANCE.toDomainList(applicationInfoDTOList);
    }

    @Override
    public Page<ApplicationInfoDTO> toDTOPage(Page<ApplicationInfo> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), ApplicationInfo2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
