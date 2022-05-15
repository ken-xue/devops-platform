package io.kenxue.cicd.domain.repository.middleware.jenkins;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.middleware.jenkins.JenkinsListQry;
import io.kenxue.cicd.coreclient.dto.middleware.jenkins.JenkinsPageQry;
import io.kenxue.cicd.domain.domain.middleware.Jenkins;
import java.util.List;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
public interface JenkinsRepository {
    void create(Jenkins jenkins);
    void update(Jenkins jenkins);
    Jenkins getById(Long id);
    List<Jenkins> list(JenkinsListQry jenkinsListQry);
    Page<Jenkins> page(JenkinsPageQry qry);
}
