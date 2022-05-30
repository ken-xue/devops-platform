package io.kenxue.devops.application.pipeline.nodeinfo.command.query;

import io.kenxue.devops.application.pipeline.nodeinfo.assembler.PipelineNodeInfo2DTOAssembler;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoDTO;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.pipeline.PipelineNodeInfoRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.pipeline.PipelineNodeInfo;
import javax.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Component
public class PipelineNodeInfoPageQryExe {

    @Resource
    private PipelineNodeInfoRepository pipelineNodeInfoRepository;
    @Resource
    private PipelineNodeInfo2DTOAssembler pipelineNodeInfo2DTOAssembler;

    public PageResponse<PipelineNodeInfoDTO> execute(PipelineNodeInfoPageQry qry) {
        Page<PipelineNodeInfo> page = pipelineNodeInfoRepository.page(qry);
        return PageResponse.of(pipelineNodeInfo2DTOAssembler.toDTOPage(page));
    }
}
