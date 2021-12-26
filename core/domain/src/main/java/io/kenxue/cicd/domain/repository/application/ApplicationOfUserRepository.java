package io.kenxue.cicd.domain.repository.application;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.application.applicationofuser.ApplicationOfUserListQry;
import io.kenxue.cicd.coreclient.dto.application.applicationofuser.ApplicationOfUserPageQry;
import io.kenxue.cicd.domain.domain.application.ApplicationOfUser;
import java.util.List;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
public interface ApplicationOfUserRepository {
    void create(ApplicationOfUser applicationOfUser);
    void update(ApplicationOfUser applicationOfUser);
    ApplicationOfUser getById(Long id);
    List<ApplicationOfUser> list(ApplicationOfUserListQry applicationOfUserListQry);
    Page<ApplicationOfUser> page(ApplicationOfUserPageQry qry);
}
