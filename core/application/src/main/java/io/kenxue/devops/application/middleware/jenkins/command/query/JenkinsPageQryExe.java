package io.kenxue.devops.application.middleware.jenkins.command.query;

import io.kenxue.devops.application.middleware.jenkins.assembler.Jenkins2DTOAssembler;
import io.kenxue.devops.coreclient.dto.middleware.jenkins.JenkinsDTO;
import io.kenxue.devops.coreclient.dto.middleware.jenkins.JenkinsPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.middleware.jenkins.JenkinsRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.middleware.Jenkins;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Component
public class JenkinsPageQryExe {

    @Resource
    private JenkinsRepository jenkinsRepository;
    @Resource
    private Jenkins2DTOAssembler jenkins2DTOAssembler;

    public PageResponse<JenkinsDTO> execute(JenkinsPageQry qry) {
        Page<Jenkins> page = jenkinsRepository.page(qry);
        return PageResponse.of(jenkins2DTOAssembler.toDTOPage(page));
    }
}
