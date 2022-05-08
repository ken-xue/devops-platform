package io.kenxue.cicd.application.pipeline.nodeinfo.command.query;

import io.kenxue.cicd.application.pipeline.nodeinfo.assembler.PipelineNodeInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoDTO;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoListQry;
import io.kenxue.cicd.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.cicd.domain.repository.pipeline.PipelineNodeInfoRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Component
public class PipelineNodeInfoListQryExe {
    @Resource
    private PipelineNodeInfoRepository pipelineNodeInfoRepository;
    @Resource
    private PipelineNodeInfo2DTOAssembler pipelineNodeInfo2DTOAssembler;

    public MultiResponse<PipelineNodeInfoDTO> execute(PipelineNodeInfoListQry qry) {
        List<PipelineNodeInfo> pipelineNodeInfoDTOList = pipelineNodeInfoRepository.list(qry);
        return MultiResponse.of(pipelineNodeInfo2DTOAssembler.toDTOList(pipelineNodeInfoDTOList));
    }
}
