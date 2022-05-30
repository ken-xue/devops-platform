package io.kenxue.devops.application.application.info.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.application.ApplicationInfoRepository;
import io.kenxue.devops.domain.domain.application.ApplicationInfo;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.ApplicationInfoDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationInfoDeleteCmdExe {
    @Resource
    private ApplicationInfoRepository applicationInfoRepository;

    public Response execute(ApplicationInfoDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            ApplicationInfo applicationInfo =applicationInfoRepository.getById(id);
            applicationInfo.deleted(UserThreadContext.get());
            applicationInfoRepository.update(applicationInfo);
        }
        return Response.success();
    }
}
