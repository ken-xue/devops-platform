package io.kenxue.devops.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.devops.infrastructure.common.convector.Convector;
import io.kenxue.devops.domain.domain.project.TaskOfUser;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskOfUserDO;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOfUser2DOConvector implements Convector<TaskOfUser,TaskOfUserDO>{
    
    public TaskOfUserDO toDO(TaskOfUser taskOfUser) {
        return TaskOfUser2DOMapStruct.INSTANCE.toDO(taskOfUser);
    }

    public TaskOfUser toDomain(TaskOfUserDO taskOfUserDO) {
        return TaskOfUser2DOMapStruct.INSTANCE.toDomain(taskOfUserDO);
    }

    public List<TaskOfUserDO> toDOList(List<TaskOfUser> taskOfUserList) {
        return TaskOfUser2DOMapStruct.INSTANCE.toDOList(taskOfUserList);
    }

    public List<TaskOfUser> toDomainList(List<TaskOfUserDO> taskOfUserDOList) {
        return TaskOfUser2DOMapStruct.INSTANCE.toDomainList(taskOfUserDOList);
    }
}
