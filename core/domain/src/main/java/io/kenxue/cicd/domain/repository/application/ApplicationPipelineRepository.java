package io.kenxue.cicd.domain.repository.application;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelineListQry;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelinePageQry;
import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public interface ApplicationPipelineRepository {
    void create(ApplicationPipeline applicationPipeline);
    void update(ApplicationPipeline applicationPipeline);
    ApplicationPipeline getById(Long id);
    List<ApplicationPipeline> list(ApplicationPipelineListQry applicationPipelineListQry);
    Page<ApplicationPipeline> page(ApplicationPipelinePageQry qry);
}
