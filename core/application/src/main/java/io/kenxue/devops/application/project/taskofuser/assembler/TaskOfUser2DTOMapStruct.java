package io.kenxue.devops.application.project.taskofuser.assembler;

import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserDTO;
import io.kenxue.devops.domain.domain.project.TaskOfUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskOfUser2DTOMapStruct {

    TaskOfUser2DTOMapStruct INSTANCE = Mappers.getMapper(TaskOfUser2DTOMapStruct.class);

    @Mappings({})
    TaskOfUserDTO toDTO(TaskOfUser taskOfUser);

    @Mappings({})
    TaskOfUser toDomain(TaskOfUserDTO taskOfUserDTO);

    List<TaskOfUserDTO> toDTOList(List<TaskOfUser> TaskOfUserList);

    List<TaskOfUser> toDomainList(List<TaskOfUserDTO> TaskOfUserDTOList);
}
