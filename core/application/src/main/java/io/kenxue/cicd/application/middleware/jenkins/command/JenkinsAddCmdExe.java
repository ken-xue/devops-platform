package io.kenxue.cicd.application.middleware.jenkins.command;

import io.kenxue.cicd.application.middleware.jenkins.assembler.Jenkins2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.middleware.jenkins.JenkinsRepository;
import io.kenxue.cicd.domain.domain.middleware.Jenkins;
import io.kenxue.cicd.coreclient.dto.middleware.jenkins.JenkinsAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Component
public class JenkinsAddCmdExe {

    @Resource
    private JenkinsRepository jenkinsRepository;
    @Resource
    private Jenkins2DTOAssembler jenkins2DTOAssembler;

    public Response execute(JenkinsAddCmd cmd) {
        Jenkins jenkins = jenkins2DTOAssembler.toDomain(cmd.getJenkinsDTO());
        jenkins.create(UserThreadContext.get());
        jenkinsRepository.create(jenkins);
        return Response.success();
    }
}
