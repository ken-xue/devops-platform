package io.kenxue.devops.application.middleware.jenkins.command.query;

import io.kenxue.devops.application.middleware.jenkins.assembler.Jenkins2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.middleware.jenkins.JenkinsDTO;
import io.kenxue.devops.coreclient.dto.middleware.jenkins.JenkinsListQry;
import io.kenxue.devops.domain.domain.middleware.Jenkins;
import io.kenxue.devops.domain.repository.middleware.jenkins.JenkinsRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Component
public class JenkinsListQryExe {
    @Resource
    private JenkinsRepository jenkinsRepository;
    @Resource
    private Jenkins2DTOAssembler jenkins2DTOAssembler;

    public MultiResponse<JenkinsDTO> execute(JenkinsListQry qry) {
        List<Jenkins> jenkinsDTOList = jenkinsRepository.list(qry);
        return MultiResponse.of(jenkins2DTOAssembler.toDTOList(jenkinsDTOList));
    }
}
