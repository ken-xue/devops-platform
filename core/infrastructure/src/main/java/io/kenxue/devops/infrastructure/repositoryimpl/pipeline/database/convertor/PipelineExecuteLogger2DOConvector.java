package io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.convertor;

import io.kenxue.devops.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.dataobject.PipelineExecuteLoggerDO;
import org.springframework.stereotype.Component;
import io.kenxue.devops.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Component
public class PipelineExecuteLogger2DOConvector implements Convector<PipelineExecuteLogger,PipelineExecuteLoggerDO>{
    
    public PipelineExecuteLoggerDO toDO(PipelineExecuteLogger pipelineExecuteLogger) {
        return PipelineExecuteLogger2DOMapStruct.INSTANCE.toDO(pipelineExecuteLogger);
    }

    public PipelineExecuteLogger toDomain(PipelineExecuteLoggerDO pipelineExecuteLoggerDO) {
        return PipelineExecuteLogger2DOMapStruct.INSTANCE.toDomain(pipelineExecuteLoggerDO);
    }

    public List<PipelineExecuteLoggerDO> toDOList(List<PipelineExecuteLogger> pipelineExecuteLoggerList) {
        return PipelineExecuteLogger2DOMapStruct.INSTANCE.toDOList(pipelineExecuteLoggerList);
    }

    public List<PipelineExecuteLogger> toDomainList(List<PipelineExecuteLoggerDO> pipelineExecuteLoggerDOList) {
        return PipelineExecuteLogger2DOMapStruct.INSTANCE.toDomainList(pipelineExecuteLoggerDOList);
    }
}
