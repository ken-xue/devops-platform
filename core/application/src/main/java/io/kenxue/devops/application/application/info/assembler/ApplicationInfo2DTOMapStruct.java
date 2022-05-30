package io.kenxue.devops.application.application.info.assembler;

import io.kenxue.devops.coreclient.dto.application.applicationinfo.ApplicationInfoDTO;
import io.kenxue.devops.domain.domain.application.ApplicationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
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
