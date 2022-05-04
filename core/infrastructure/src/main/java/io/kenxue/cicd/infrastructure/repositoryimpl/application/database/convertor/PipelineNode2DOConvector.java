package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.cicd.domain.domain.pipeline.PipelineNode;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.PipelineNodeDO;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Convector;
import java.util.List;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Component
public class PipelineNode2DOConvector implements Convector<PipelineNode,PipelineNodeDO>{
    
    public PipelineNodeDO toDO(PipelineNode pipelineNode) {
        return PipelineNode2DOMapStruct.INSTANCE.toDO(pipelineNode);
    }

    public PipelineNode toDomain(PipelineNodeDO pipelineNodeDO) {
        return PipelineNode2DOMapStruct.INSTANCE.toDomain(pipelineNodeDO);
    }

    public List<PipelineNodeDO> toDOList(List<PipelineNode> pipelineNodeList) {
        return PipelineNode2DOMapStruct.INSTANCE.toDOList(pipelineNodeList);
    }

    public List<PipelineNode> toDomainList(List<PipelineNodeDO> pipelineNodeDOList) {
        return PipelineNode2DOMapStruct.INSTANCE.toDomainList(pipelineNodeDOList);
    }
}
