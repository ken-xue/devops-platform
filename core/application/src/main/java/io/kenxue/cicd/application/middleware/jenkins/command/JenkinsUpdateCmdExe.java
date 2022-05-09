package io.kenxue.cicd.application.middleware.jenkins.command;

import io.kenxue.cicd.application.middleware.jenkins.assembler.Jenkins2DTOAssembler;
import io.kenxue.cicd.domain.domain.jenkins.Jenkins;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.jenkins.jenkins.JenkinsUpdateCmd;
import io.kenxue.cicd.domain.repository.middleware.jenkins.JenkinsRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Component
public class JenkinsUpdateCmdExe {

    @Resource
    private JenkinsRepository jenkinsRepository;
    @Resource
    private Jenkins2DTOAssembler jenkins2DTOAssembler;

    public Response execute(JenkinsUpdateCmd cmd) {
        Jenkins jenkins = jenkins2DTOAssembler.toDomain(cmd.getJenkinsDTO());
        jenkinsRepository.update(jenkins);
        return Response.success();
    }
}