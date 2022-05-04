package io.kenxue.cicd.application.pipeline.pipelinenode.command.query;

import io.kenxue.cicd.application.pipeline.pipelinenode.assembler.PipelineNode2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenode.PipelineNodeDTO;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenode.PipelineNodePageQry;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.domain.repository.application.PipelineNodeRepository;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.domain.domain.pipeline.PipelineNode;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Component
public class PipelineNodePageQryExe {

    @Resource
    private PipelineNodeRepository pipelineNodeRepository;
    @Resource
    private PipelineNode2DTOAssembler pipelineNode2DTOAssembler;

    public PageResponse<PipelineNodeDTO> execute(PipelineNodePageQry qry) {
        Page<PipelineNode> page = pipelineNodeRepository.page(qry);
        return PageResponse.of(pipelineNode2DTOAssembler.toDTOPage(page));
    }
}
