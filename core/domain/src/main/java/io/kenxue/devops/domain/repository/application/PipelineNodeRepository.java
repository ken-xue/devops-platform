package io.kenxue.devops.domain.repository.application;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.PipelineNodeListQry;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.PipelineNodePageQry;
import io.kenxue.devops.domain.domain.pipeline.PipelineNode;
import java.util.List;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
public interface PipelineNodeRepository {
    void create(PipelineNode pipelineNode);
    void update(PipelineNode pipelineNode);
    PipelineNode getById(Long id);
    List<PipelineNode> list(PipelineNodeListQry pipelineNodeListQry);
    Page<PipelineNode> page(PipelineNodePageQry qry);
}
