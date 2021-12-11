package io.kenxue.cicd.application.application.sys.userofrole.service;

import io.kenxue.cicd.application.application.sys.userofrole.command.UserOfRoleAddCmdExe;
import io.kenxue.cicd.application.application.sys.userofrole.command.UserOfRoleDeleteCmdExe;
import io.kenxue.cicd.application.application.sys.userofrole.command.UserOfRoleUpdateCmdExe;
import io.kenxue.cicd.application.application.sys.userofrole.command.query.UserOfRoleGetQryExe;
import io.kenxue.cicd.application.application.sys.userofrole.command.query.UserOfRoleListQryExe;
import io.kenxue.cicd.coreclient.api.sys.UserOfRoleAppService;
import io.kenxue.cicd.coreclient.dto.sys.userofrole.*;
import io.kenxue.cicd.application.application.sys.userofrole.command.query.UserOfRolePageQryExe;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Service
public class UserOfRoleAppAppServiceImpl implements UserOfRoleAppService {
    @Resource
    private UserOfRoleAddCmdExe userOfRoleAddCmdExe;
    @Resource
    private UserOfRoleUpdateCmdExe userOfRoleUpdateCmdExe;
    @Resource
    private UserOfRoleGetQryExe userOfRoleGetQryExe;
    @Resource
    private UserOfRoleListQryExe userOfRoleListQryExe;
    @Resource
    private UserOfRolePageQryExe userOfRolePageQryExe;
    @Resource
    private UserOfRoleDeleteCmdExe userOfRoleDeleteCmdExe;

    
    public Response add(UserOfRoleAddCmd userOfRoleAddCmd) {
        return  userOfRoleAddCmdExe.execute(userOfRoleAddCmd);
    }

    
    public Response update(UserOfRoleUpdateCmd cmd) {
        return userOfRoleUpdateCmdExe.execute(cmd);
    }

    
    public SingleResponse<UserOfRoleDTO> getById(UserOfRoleGetQry qry) {
        return userOfRoleGetQryExe.execute(qry);
    }

    
    public MultiResponse<UserOfRoleDTO> list(UserOfRoleListQry qry) {
        return userOfRoleListQryExe.execute(qry);
    }
    
    public Response delete(UserOfRoleDeleteCmd userOfRoleDeleteCmd) {
        return userOfRoleDeleteCmdExe.execute(userOfRoleDeleteCmd);
    }

    
    public PageResponse<UserOfRoleDTO> page(UserOfRolePageQry userOfRolePageQry) {
        return userOfRolePageQryExe.execute(userOfRolePageQry);
    }

}
