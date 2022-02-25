package io.kenxue.cicd.application.application.application.pipelinenode.command.query;

import io.kenxue.cicd.application.application.application.pipelinenode.assembler.PipelineNode2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.application.pipelinenode.PipelineNodeDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.application.pipelinenode.PipelineNodeGetQry;
import io.kenxue.cicd.domain.repository.application.PipelineNodeRepository;
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
