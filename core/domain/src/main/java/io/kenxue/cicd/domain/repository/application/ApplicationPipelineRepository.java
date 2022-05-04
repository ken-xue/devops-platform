package io.kenxue.cicd.domain.repository.application;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.ApplicationPipelineListQry;
import io.kenxue.cicd.coreclient.dto.pipeline.pipeline.ApplicationPipelinePageQry;
import io.kenxue.cicd.domain.domain.pipeline.Pipeline;

import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public interface ApplicationPipelineRepository {
    void create(Pipeline pipeline);
    void update(Pipeline pipeline);
    Pipeline getById(Long id);
    List<Pipeline> list(ApplicationPipelineListQry applicationPipelineListQry);
    Page<Pipeline> page(ApplicationPipelinePageQry qry);

    /**
     * 通过pipeline name查询
     * @param name
     * @return
     */
    Pipeline getByName(String name);
}
