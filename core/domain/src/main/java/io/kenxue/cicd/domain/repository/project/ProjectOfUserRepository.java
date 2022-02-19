package io.kenxue.cicd.domain.repository.project;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.ProjectOfUserListQry;
import io.kenxue.cicd.coreclient.dto.project.projectofuser.ProjectOfUserPageQry;
import io.kenxue.cicd.domain.domain.project.ProjectOfUser;
import java.util.List;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
public interface ProjectOfUserRepository {
    void create(ProjectOfUser projectOfUser);
    void update(ProjectOfUser projectOfUser);
    ProjectOfUser getById(Long id);
    List<ProjectOfUser> list(ProjectOfUserListQry projectOfUserListQry);
    Page<ProjectOfUser> page(ProjectOfUserPageQry qry);
}
