package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.domain.domain.project.TaskOfUser;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskOfUserDO;
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
public interface TaskOfUser2DOMapStruct {

    TaskOfUser2DOMapStruct INSTANCE = Mappers.getMapper(TaskOfUser2DOMapStruct.class);

    @Mappings({})
    TaskOfUserDO toDO(TaskOfUser taskOfUser);

    @Mappings({})
    TaskOfUser toDomain(TaskOfUserDO taskOfUserDO);

    List<TaskOfUserDO> toDOList(List<TaskOfUser> taskOfUserList);

    List<TaskOfUser> toDomainList(List<TaskOfUserDO> taskOfUserDOList);
}
