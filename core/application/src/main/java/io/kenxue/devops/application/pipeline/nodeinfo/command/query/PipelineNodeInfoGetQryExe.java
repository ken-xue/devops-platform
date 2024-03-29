package io.kenxue.devops.application.pipeline.nodeinfo.command.query;

import io.kenxue.devops.application.pipeline.nodeinfo.assembler.PipelineNodeInfo2DTOAssembler;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoGetQry;
import io.kenxue.devops.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.devops.domain.repository.pipeline.PipelineNodeInfoRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.Objects;

/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Component
public class PipelineNodeInfoGetQryExe {

    @Resource
    private PipelineNodeInfoRepository pipelineNodeInfoRepository;
    @Resource
    private PipelineNodeInfo2DTOAssembler pipelineNodeInfo2DTOAssembler;

    public SingleResponse<PipelineNodeInfoDTO> execute(PipelineNodeInfoGetQry qry) {
        PipelineNodeInfo ret = pipelineNodeInfoRepository.getById(qry.getId());
        if (Objects.isNull(ret)&& StringUtils.isNotBlank(qry.getNodeUuid())){
            ret = pipelineNodeInfoRepository.getByNodeId(qry.getNodeUuid());
        }
        return SingleResponse.of(pipelineNodeInfo2DTOAssembler.toDTO(ret));
    }

}
