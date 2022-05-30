package io.kenxue.devops.application.application.config.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.application.ApplicationConfigRepository;
import io.kenxue.devops.domain.domain.application.ApplicationConfig;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationConfigDeleteCmdExe {
    @Resource
    private ApplicationConfigRepository applicationConfigRepository;

    public Response execute(ApplicationConfigDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            ApplicationConfig applicationConfig =applicationConfigRepository.getById(id);
            applicationConfig.deleted(UserThreadContext.get());
            applicationConfigRepository.update(applicationConfig);
        }
        return Response.success();
    }
}
