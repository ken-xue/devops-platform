package io.kenxue.cicd.domain.repository.application;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.application.applicationinfo.ApplicationInfoListQry;
import io.kenxue.cicd.coreclient.dto.application.applicationinfo.ApplicationInfoPageQry;
import io.kenxue.cicd.domain.domain.application.ApplicationInfo;
import java.util.List;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public interface ApplicationInfoRepository {
    void create(ApplicationInfo applicationInfo);
    void update(ApplicationInfo applicationInfo);
    ApplicationInfo getById(Long id);
    List<ApplicationInfo> list(ApplicationInfoListQry applicationInfoListQry);
    Page<ApplicationInfo> page(ApplicationInfoPageQry qry);

    ApplicationInfo getByUuid(String applicationUuid);
}
