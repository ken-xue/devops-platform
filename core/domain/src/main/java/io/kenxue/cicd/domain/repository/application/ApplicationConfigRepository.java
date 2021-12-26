package io.kenxue.cicd.domain.repository.application;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.application.applicationconfig.ApplicationConfigListQry;
import io.kenxue.cicd.coreclient.dto.application.applicationconfig.ApplicationConfigPageQry;
import io.kenxue.cicd.domain.domain.application.ApplicationConfig;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
public interface ApplicationConfigRepository {
    void create(ApplicationConfig applicationConfig);
    void update(ApplicationConfig applicationConfig);
    ApplicationConfig getById(Long id);
    List<ApplicationConfig> list(ApplicationConfigListQry applicationConfigListQry);
    Page<ApplicationConfig> page(ApplicationConfigPageQry qry);
}
