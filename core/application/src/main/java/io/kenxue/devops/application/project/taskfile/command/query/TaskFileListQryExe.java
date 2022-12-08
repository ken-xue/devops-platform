package io.kenxue.devops.application.project.taskfile.command.query;

import io.kenxue.devops.application.project.taskfile.assembler.TaskFile2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileDTO;
import io.kenxue.devops.coreclient.dto.project.taskfile.TaskFileListQry;
import io.kenxue.devops.domain.domain.project.TaskFile;
import io.kenxue.devops.domain.repository.project.TaskFileRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskFileListQryExe {
    @Resource
    private TaskFileRepository taskFileRepository;
    @Resource
    private TaskFile2DTOAssembler taskFile2DTOAssembler;

    public MultiResponse<TaskFileDTO> execute(TaskFileListQry qry) {
        List<TaskFile> taskFileDTOList = taskFileRepository.list(qry);
        return MultiResponse.of(taskFile2DTOAssembler.toDTOList(taskFileDTOList));
    }
}
