package io.kenxue.devops.domain.repository.pipeline;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelineListQry;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelinePageQry;
import io.kenxue.devops.domain.domain.pipeline.Pipeline;

import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public interface PipelineRepository {
    void create(Pipeline pipeline);
    void update(Pipeline pipeline);
    Pipeline getById(Long id);
    List<Pipeline> list(PipelineListQry pipelineListQry);
    Page<Pipeline> page(PipelinePageQry qry);

    /**
     * 通过pipeline name查询
     * @param name
     * @return
     */
    Pipeline getByName(String name);

    Pipeline getByUUID(String uuid);
}
