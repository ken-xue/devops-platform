package io.kenxue.devops.domain.repository.application;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigListQry;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigPageQry;
import io.kenxue.devops.domain.domain.application.ApplicationConfig;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public interface ApplicationConfigRepository {
    void create(ApplicationConfig applicationConfig);
    void update(ApplicationConfig applicationConfig);
    ApplicationConfig getById(Long id);

    ApplicationConfig getBUuid(String uuid);

    List<ApplicationConfig> list(ApplicationConfigListQry applicationConfigListQry);
    Page<ApplicationConfig> page(ApplicationConfigPageQry qry);
}
