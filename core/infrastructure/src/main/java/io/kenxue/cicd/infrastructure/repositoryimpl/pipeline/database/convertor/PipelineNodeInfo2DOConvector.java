package io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.convertor;

import io.kenxue.cicd.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.dataobject.PipelineNodeInfoDO;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Component
public class PipelineNodeInfo2DOConvector implements Convector<PipelineNodeInfo,PipelineNodeInfoDO>{
    
    public PipelineNodeInfoDO toDO(PipelineNodeInfo pipelineNodeInfo) {
        return PipelineNodeInfo2DOMapStruct.INSTANCE.toDO(pipelineNodeInfo);
    }

    public PipelineNodeInfo toDomain(PipelineNodeInfoDO pipelineNodeInfoDO) {
        return PipelineNodeInfo2DOMapStruct.INSTANCE.toDomain(pipelineNodeInfoDO);
    }

    public List<PipelineNodeInfoDO> toDOList(List<PipelineNodeInfo> pipelineNodeInfoList) {
        return PipelineNodeInfo2DOMapStruct.INSTANCE.toDOList(pipelineNodeInfoList);
    }

    public List<PipelineNodeInfo> toDomainList(List<PipelineNodeInfoDO> pipelineNodeInfoDOList) {
        return PipelineNodeInfo2DOMapStruct.INSTANCE.toDomainList(pipelineNodeInfoDOList);
    }
}
