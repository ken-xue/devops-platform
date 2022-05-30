package io.kenxue.devops.domain.repository.project;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoListQry;
import io.kenxue.devops.coreclient.dto.project.projectinfo.ProjectInfoPageQry;
import io.kenxue.devops.domain.domain.project.ProjectInfo;
import java.util.List;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
public interface ProjectInfoRepository {
    void create(ProjectInfo projectInfo);
    void update(ProjectInfo projectInfo);
    ProjectInfo getById(Long id);
    List<ProjectInfo> list(ProjectInfoListQry projectInfoListQry);
    Page<ProjectInfo> page(ProjectInfoPageQry qry);
}
