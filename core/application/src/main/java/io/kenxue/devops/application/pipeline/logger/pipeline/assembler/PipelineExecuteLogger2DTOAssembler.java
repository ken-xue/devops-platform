package io.kenxue.devops.application.pipeline.logger.pipeline.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerDTO;
import io.kenxue.devops.domain.domain.pipeline.PipelineExecuteLogger;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Component
public class PipelineExecuteLogger2DTOAssembler implements Assembler<PipelineExecuteLoggerDTO, PipelineExecuteLogger> {

    @Override
    public PipelineExecuteLoggerDTO toDTO(PipelineExecuteLogger pipelineExecuteLogger) {
        return PipelineExecuteLogger2DTOMapStruct.INSTANCE.toDTO(pipelineExecuteLogger);
    }

    @Override
    public PipelineExecuteLogger toDomain(PipelineExecuteLoggerDTO pipelineExecuteLoggerDTO) {
        return PipelineExecuteLogger2DTOMapStruct.INSTANCE.toDomain(pipelineExecuteLoggerDTO);
    }

    @Override
    public List<PipelineExecuteLoggerDTO> toDTOList(List<PipelineExecuteLogger> pipelineExecuteLoggerList) {
        return PipelineExecuteLogger2DTOMapStruct.INSTANCE.toDTOList(pipelineExecuteLoggerList);
    }

    @Override
    public List<PipelineExecuteLogger> toDomainList(List<PipelineExecuteLoggerDTO> pipelineExecuteLoggerDTOList) {
        return PipelineExecuteLogger2DTOMapStruct.INSTANCE.toDomainList(pipelineExecuteLoggerDTOList);
    }

    @Override
    public Page<PipelineExecuteLoggerDTO> toDTOPage(Page<PipelineExecuteLogger> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), PipelineExecuteLogger2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
