package io.kenxue.cicd.application.application.application.applicationinfo.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.application.ApplicationInfoRepository;
import io.kenxue.cicd.domain.domain.application.ApplicationInfo;
import io.kenxue.cicd.coreclient.dto.application.applicationinfo.ApplicationInfoDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 17:17:54
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
