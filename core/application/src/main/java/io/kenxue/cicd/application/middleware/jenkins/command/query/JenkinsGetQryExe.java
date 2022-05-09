package io.kenxue.cicd.application.middleware.jenkins.command.query;

import io.kenxue.cicd.application.middleware.jenkins.assembler.Jenkins2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.jenkins.jenkins.JenkinsDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.jenkins.jenkins.JenkinsGetQry;
import io.kenxue.cicd.domain.repository.middleware.jenkins.JenkinsRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Component
public class JenkinsGetQryExe {

    @Resource
    private JenkinsRepository jenkinsRepository;
    @Resource
    private Jenkins2DTOAssembler jenkins2DTOAssembler;

    public SingleResponse<JenkinsDTO> execute(JenkinsGetQry qry) {
        return SingleResponse.of(jenkins2DTOAssembler.toDTO(jenkinsRepository.getById(qry.getId())));
    }

}
