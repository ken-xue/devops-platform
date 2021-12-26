package io.kenxue.cicd.application.application.application.applicationinfo.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.application.applicationinfo.ApplicationInfoDTO;
import io.kenxue.cicd.domain.domain.application.ApplicationInfo;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 17:17:54
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
