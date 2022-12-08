package io.kenxue.devops.application.sys.config.service;

import io.kenxue.devops.coreclient.api.sys.ConfigAppService;
import io.kenxue.devops.coreclient.dto.sys.config.*;
import io.kenxue.devops.application.sys.config.command.ConfigAddCmdExe;
import io.kenxue.devops.application.sys.config.command.ConfigDeleteCmdExe;
import io.kenxue.devops.application.sys.config.command.ConfigUpdateCmdExe;
import io.kenxue.devops.application.sys.config.command.query.ConfigGetQryExe;
import io.kenxue.devops.application.sys.config.command.query.ConfigListQryExe;
import io.kenxue.devops.application.sys.config.command.query.ConfigPageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Service
public class ConfigAppServiceImpl implements ConfigAppService {
    @Resource
    private ConfigAddCmdExe configAddCmdExe;
    @Resource
    private ConfigUpdateCmdExe configUpdateCmdExe;
    @Resource
    private ConfigGetQryExe configGetQryExe;
    @Resource
    private ConfigListQryExe configListQryExe;
    @Resource
    private ConfigPageQryExe configPageQryExe;
    @Resource
    private ConfigDeleteCmdExe configDeleteCmdExe;

    public Response add(ConfigAddCmd configAddCmd) {
        return  configAddCmdExe.execute(configAddCmd);
    }

    public Response update(ConfigUpdateCmd cmd) {
        return configUpdateCmdExe.execute(cmd);
    }

    public SingleResponse<ConfigDTO> getById(ConfigGetQry qry) {
        return configGetQryExe.execute(qry);
    }

    public MultiResponse<ConfigDTO> list(ConfigListQry qry) {
        return configListQryExe.execute(qry);
    }

    public Response delete(ConfigDeleteCmd configDeleteCmd) {
        return configDeleteCmdExe.execute(configDeleteCmd);
    }

    public PageResponse<ConfigDTO> page(ConfigPageQry configPageQry) {
        return configPageQryExe.execute(configPageQry);
    }

}
