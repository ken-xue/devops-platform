package io.kenxue.devops.application.application.appofuser.assembler;

import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserDTO;
import io.kenxue.devops.domain.domain.application.ApplicationOfUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationOfUser2DTOMapStruct {

    ApplicationOfUser2DTOMapStruct INSTANCE = Mappers.getMapper(ApplicationOfUser2DTOMapStruct.class);

    @Mappings({})
    ApplicationOfUserDTO toDTO(ApplicationOfUser applicationOfUser);

    @Mappings({})
    ApplicationOfUser toDomain(ApplicationOfUserDTO applicationOfUserDTO);

    List<ApplicationOfUserDTO> toDTOList(List<ApplicationOfUser> ApplicationOfUserList);

    List<ApplicationOfUser> toDomainList(List<ApplicationOfUserDTO> ApplicationOfUserDTOList);
}
