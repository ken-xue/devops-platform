package io.kenxue.devops.application.project.taskofuser.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserDTO;
import io.kenxue.devops.domain.domain.project.TaskOfUser;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOfUser2DTOAssembler implements Assembler<TaskOfUserDTO, TaskOfUser> {

    @Override
    public TaskOfUserDTO toDTO(TaskOfUser taskOfUser) {
        return TaskOfUser2DTOMapStruct.INSTANCE.toDTO(taskOfUser);
    }

    @Override
    public TaskOfUser toDomain(TaskOfUserDTO taskOfUserDTO) {
        return TaskOfUser2DTOMapStruct.INSTANCE.toDomain(taskOfUserDTO);
    }

    @Override
    public List<TaskOfUserDTO> toDTOList(List<TaskOfUser> taskOfUserList) {
        return TaskOfUser2DTOMapStruct.INSTANCE.toDTOList(taskOfUserList);
    }

    @Override
    public List<TaskOfUser> toDomainList(List<TaskOfUserDTO> taskOfUserDTOList) {
        return TaskOfUser2DTOMapStruct.INSTANCE.toDomainList(taskOfUserDTOList);
    }

    @Override
    public Page<TaskOfUserDTO> toDTOPage(Page<TaskOfUser> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), TaskOfUser2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
