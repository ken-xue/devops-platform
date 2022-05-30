package io.kenxue.devops.application.sys.log.service;

import io.kenxue.devops.application.sys.log.command.LogAddCmdExe;
import io.kenxue.devops.application.sys.log.command.LogDeleteCmdExe;
import io.kenxue.devops.application.sys.log.command.LogUpdateCmdExe;
import io.kenxue.devops.application.sys.log.command.query.LogGetQryExe;
import io.kenxue.devops.application.sys.log.command.query.LogListQryExe;
import io.kenxue.devops.application.sys.log.command.query.LogPageQryExe;
import io.kenxue.devops.coreclient.api.sys.LogAppService;
import io.kenxue.devops.coreclient.dto.sys.log.*;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@Service
public class LogAppAppServiceImpl implements LogAppService {
    @Resource
    private LogAddCmdExe logAddCmdExe;
    @Resource
    private LogUpdateCmdExe logUpdateCmdExe;
    @Resource
    private LogGetQryExe logGetQryExe;
    @Resource
    private LogListQryExe logListQryExe;
    @Resource
    private LogPageQryExe logPageQryExe;
    @Resource
    private LogDeleteCmdExe logDeleteCmdExe;

    
    public Response add(LogAddCmd logAddCmd) {
        return  logAddCmdExe.execute(logAddCmd);
    }

    
    public Response update(LogUpdateCmd cmd) {
        return logUpdateCmdExe.execute(cmd);
    }

    
    public SingleResponse<LogDTO> getById(LogGetQry qry) {
        return logGetQryExe.execute(qry);
    }

    
    public MultiResponse<LogDTO> list(LogListQry qry) {
        return logListQryExe.execute(qry);
    }
    
    public Response delete(LogDeleteCmd logDeleteCmd) {
        return logDeleteCmdExe.execute(logDeleteCmd);
    }

    
    public PageResponse<LogDTO> page(LogPageQry logPageQry) {
        return logPageQryExe.execute(logPageQry);
    }

}
