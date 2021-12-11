package io.kenxue.cicd.domain.repository.sys;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.sys.user.UserListQry;
import io.kenxue.cicd.coreclient.dto.sys.user.UserPageQry;
import io.kenxue.cicd.domain.domain.sys.User;

import java.util.List;

/**
 * UserRepository
 *
 * @date 2020-07-02 12:16 PM
 */
public interface UserRepository {
    void create(User user);
    void update(User user);
    User getById(Long id);
    User getByUserId(String userId);
    Page<User> page(UserPageQry qry);
    List<User> list(UserListQry qry);
}
