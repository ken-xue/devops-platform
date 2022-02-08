package io.kenxue.cicd.application.application.application.appconfig.command;

import io.kenxue.cicd.application.application.application.appconfig.assembler.ApplicationConfig2DTOAssembler;
import io.kenxue.cicd.domain.domain.application.ApplicationConfig;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.application.applicationconfig.ApplicationConfigUpdateCmd;
import io.kenxue.cicd.domain.repository.application.ApplicationConfigRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationConfigUpdateCmdExe {

    @Resource
    private ApplicationConfigRepository applicationConfigRepository;
    @Resource
    private ApplicationConfig2DTOAssembler applicationConfig2DTOAssembler;

    public Response execute(ApplicationConfigUpdateCmd cmd) {
        ApplicationConfig applicationConfig = applicationConfig2DTOAssembler.toDomain(cmd.getApplicationConfigDTO());
        applicationConfigRepository.update(applicationConfig);
        return Response.success();
    }
}