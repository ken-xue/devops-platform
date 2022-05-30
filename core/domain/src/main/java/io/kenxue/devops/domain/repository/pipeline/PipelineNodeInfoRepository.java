package io.kenxue.devops.domain.repository.pipeline;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoListQry;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoPageQry;
import io.kenxue.devops.domain.domain.pipeline.PipelineNodeInfo;
import java.util.List;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
public interface PipelineNodeInfoRepository {
    void create(PipelineNodeInfo pipelineNodeInfo);
    void update(PipelineNodeInfo pipelineNodeInfo);
    PipelineNodeInfo getById(Long id);
    List<PipelineNodeInfo> list(PipelineNodeInfoListQry pipelineNodeInfoListQry);
    Page<PipelineNodeInfo> page(PipelineNodeInfoPageQry qry);

    /**
     * 通过节点id获取节点详情
     * @param nodeUuid
     * @return
     */
    PipelineNodeInfo getByNodeId(String nodeUuid);
}
