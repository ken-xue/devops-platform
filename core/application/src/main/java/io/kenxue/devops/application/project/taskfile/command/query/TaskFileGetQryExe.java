package io.kenxue.devops.application.project.taskfile.command.query;

import io.kenxue.devops.application.project.taskfile.assembler.TaskFile2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileGetQry;
import io.kenxue.devops.domain.repository.project.TaskFileRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskFileGetQryExe {

    @Resource
    private TaskFileRepository taskFileRepository;
    @Resource
    private TaskFile2DTOAssembler taskFile2DTOAssembler;

    public SingleResponse<TaskFileDTO> execute(TaskFileGetQry qry) {
        return SingleResponse.of(taskFile2DTOAssembler.toDTO(taskFileRepository.getById(qry.getId())));
    }

}
