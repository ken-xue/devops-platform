package io.kenxue.devops.application.pipeline.node.command.query;

import io.kenxue.devops.application.pipeline.node.assembler.PipelineNode2DTOAssembler;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.PipelineNodeDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.PipelineNodeGetQry;
import io.kenxue.devops.domain.repository.application.PipelineNodeRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Component
public class PipelineNodeGetQryExe {

    @Resource
    private PipelineNodeRepository pipelineNodeRepository;
    @Resource
    private PipelineNode2DTOAssembler pipelineNode2DTOAssembler;

    public SingleResponse<PipelineNodeDTO> execute(PipelineNodeGetQry qry) {
        return SingleResponse.of(pipelineNode2DTOAssembler.toDTO(pipelineNodeRepository.getById(qry.getId())));
    }

}
