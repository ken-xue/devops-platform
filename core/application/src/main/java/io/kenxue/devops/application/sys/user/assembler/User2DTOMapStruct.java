package io.kenxue.devops.application.sys.user.assembler;

import io.kenxue.devops.domain.domain.sys.User;
import io.kenxue.devops.coreclient.dto.sys.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Author: 麦奇
 * @Date: 21-11-26 下午9:31
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface User2DTOMapStruct {

    User2DTOMapStruct INSTANCE = Mappers.getMapper(User2DTOMapStruct.class);

    @Mappings({})
    UserDTO toDTO(User user);

    @Mappings({})
    User toDomain(UserDTO userCO);


    List<User> toDomainList(List<UserDTO> userDTOList);


    List<UserDTO> toDTOList(List<User> userList);
}
