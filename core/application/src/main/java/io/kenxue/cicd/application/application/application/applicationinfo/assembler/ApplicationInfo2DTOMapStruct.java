package io.kenxue.cicd.application.application.application.applicationinfo.assembler;

import io.kenxue.cicd.coreclient.dto.application.applicationinfo.ApplicationInfoDTO;
import io.kenxue.cicd.domain.domain.application.ApplicationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationInfo2DTOMapStruct {

    ApplicationInfo2DTOMapStruct INSTANCE = Mappers.getMapper(ApplicationInfo2DTOMapStruct.class);

    @Mappings({})
    ApplicationInfoDTO toDTO(ApplicationInfo applicationInfo);

    @Mappings({})
    ApplicationInfo toDomain(ApplicationInfoDTO applicationInfoDTO);

    List<ApplicationInfoDTO> toDTOList(List<ApplicationInfo> ApplicationInfoList);

    List<ApplicationInfo> toDomainList(List<ApplicationInfoDTO> ApplicationInfoDTOList);
}
