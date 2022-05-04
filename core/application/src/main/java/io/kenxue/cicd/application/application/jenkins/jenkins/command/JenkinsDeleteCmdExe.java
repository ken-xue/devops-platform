package io.kenxue.cicd.application.application.jenkins.jenkins.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.jenkins.JenkinsRepository;
import io.kenxue.cicd.domain.domain.jenkins.Jenkins;
import io.kenxue.cicd.coreclient.dto.jenkins.jenkins.JenkinsDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Component
public class JenkinsDeleteCmdExe {
    @Resource
    private JenkinsRepository jenkinsRepository;

    public Response execute(JenkinsDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            Jenkins jenkins =jenkinsRepository.getById(id);
            jenkins.deleted(UserThreadContext.get());
            jenkinsRepository.update(jenkins);
        }
        return Response.success();
    }
}
