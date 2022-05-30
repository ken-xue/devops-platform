package io.kenxue.devops.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.devops.domain.domain.pipeline.Pipeline;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject.ApplicationPipelineDO;
import org.springframework.stereotype.Component;
import io.kenxue.devops.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationPipeline2DOConvector implements Convector<Pipeline,ApplicationPipelineDO>{
    
    public ApplicationPipelineDO toDO(Pipeline pipeline) {
        return ApplicationPipeline2DOMapStruct.INSTANCE.toDO(pipeline);
    }

    public Pipeline toDomain(ApplicationPipelineDO applicationPipelineDO) {
        return ApplicationPipeline2DOMapStruct.INSTANCE.toDomain(applicationPipelineDO);
    }

    public List<ApplicationPipelineDO> toDOList(List<Pipeline> pipelineList) {
        return ApplicationPipeline2DOMapStruct.INSTANCE.toDOList(pipelineList);
    }

    public List<Pipeline> toDomainList(List<ApplicationPipelineDO> applicationPipelineDOList) {
        return ApplicationPipeline2DOMapStruct.INSTANCE.toDomainList(applicationPipelineDOList);
    }
}
