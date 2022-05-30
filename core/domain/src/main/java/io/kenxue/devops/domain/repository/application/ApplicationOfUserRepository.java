package io.kenxue.devops.domain.repository.application;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserListQry;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserPageQry;
import io.kenxue.devops.domain.domain.application.ApplicationOfUser;
import java.util.List;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public interface ApplicationOfUserRepository {
    void create(ApplicationOfUser applicationOfUser);
    void update(ApplicationOfUser applicationOfUser);
    ApplicationOfUser getById(Long id);
    List<ApplicationOfUser> list(ApplicationOfUserListQry applicationOfUserListQry);
    Page<ApplicationOfUser> page(ApplicationOfUserPageQry qry);
}
