package io.kenxue.devops.application.project.taskoperatorlogger.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerDTO;
import io.kenxue.devops.domain.domain.project.TaskOperatorLogger;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOperatorLogger2DTOAssembler implements Assembler<TaskOperatorLoggerDTO, TaskOperatorLogger> {

    @Override
    public TaskOperatorLoggerDTO toDTO(TaskOperatorLogger taskOperatorLogger) {
        return TaskOperatorLogger2DTOMapStruct.INSTANCE.toDTO(taskOperatorLogger);
    }

    @Override
    public TaskOperatorLogger toDomain(TaskOperatorLoggerDTO taskOperatorLoggerDTO) {
        return TaskOperatorLogger2DTOMapStruct.INSTANCE.toDomain(taskOperatorLoggerDTO);
    }

    @Override
    public List<TaskOperatorLoggerDTO> toDTOList(List<TaskOperatorLogger> taskOperatorLoggerList) {
        return TaskOperatorLogger2DTOMapStruct.INSTANCE.toDTOList(taskOperatorLoggerList);
    }

    @Override
    public List<TaskOperatorLogger> toDomainList(List<TaskOperatorLoggerDTO> taskOperatorLoggerDTOList) {
        return TaskOperatorLogger2DTOMapStruct.INSTANCE.toDomainList(taskOperatorLoggerDTOList);
    }

    @Override
    public Page<TaskOperatorLoggerDTO> toDTOPage(Page<TaskOperatorLogger> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), TaskOperatorLogger2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
