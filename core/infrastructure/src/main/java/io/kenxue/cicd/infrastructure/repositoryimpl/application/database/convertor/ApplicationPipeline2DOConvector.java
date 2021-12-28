package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationPipelineDO;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Convector;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationPipeline2DOConvector implements Convector<ApplicationPipeline,ApplicationPipelineDO>{
    
    public ApplicationPipelineDO toDO(ApplicationPipeline applicationPipeline) {
        return ApplicationPipeline2DOMapStruct.INSTANCE.toDO(applicationPipeline);
    }

    public ApplicationPipeline toDomain(ApplicationPipelineDO applicationPipelineDO) {
        return ApplicationPipeline2DOMapStruct.INSTANCE.toDomain(applicationPipelineDO);
    }

    public List<ApplicationPipelineDO> toDOList(List<ApplicationPipeline> applicationPipelineList) {
        return ApplicationPipeline2DOMapStruct.INSTANCE.toDOList(applicationPipelineList);
    }

    public List<ApplicationPipeline> toDomainList(List<ApplicationPipelineDO> applicationPipelineDOList) {
        return ApplicationPipeline2DOMapStruct.INSTANCE.toDomainList(applicationPipelineDOList);
    }
}
