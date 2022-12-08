package io.kenxue.devops.application.sys.roleofmenu.service;

import io.kenxue.devops.application.sys.roleofmenu.command.query.RoleOfMenuListQryExe;
import io.kenxue.devops.application.sys.roleofmenu.command.query.RoleOfMenuPageQryExe;
import io.kenxue.devops.application.sys.roleofmenu.command.RoleOfMenuAddCmdExe;
import io.kenxue.devops.application.sys.roleofmenu.command.RoleOfMenuDeleteCmdExe;
import io.kenxue.devops.application.sys.roleofmenu.command.query.RoleOfMenuGetQryExe;
import io.kenxue.devops.coreclient.api.sys.RoleOfMenuAppService;
import io.kenxue.devops.application.sys.roleofmenu.command.RoleOfMenuUpdateCmdExe;
import io.kenxue.devops.coreclient.dto.sys.roleofmenu.*;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Service
public class RoleOfMenuAppAppServiceImpl implements RoleOfMenuAppService {
    @Resource
    private RoleOfMenuAddCmdExe roleOfMenuAddCmdExe;
    @Resource
    private RoleOfMenuUpdateCmdExe roleOfMenuUpdateCmdExe;
    @Resource
    private RoleOfMenuGetQryExe roleOfMenuGetQryExe;
    @Resource
    private RoleOfMenuListQryExe roleOfMenuListQryExe;
    @Resource
    private RoleOfMenuPageQryExe roleOfMenuPageQryExe;
    @Resource
    private RoleOfMenuDeleteCmdExe roleOfMenuDeleteCmdExe;

    
    public Response add(RoleOfMenuAddCmd roleOfMenuAddCmd) {
        return  roleOfMenuAddCmdExe.execute(roleOfMenuAddCmd);
    }

    
    public Response update(RoleOfMenuUpdateCmd cmd) {
        return roleOfMenuUpdateCmdExe.execute(cmd);
    }

    
    public SingleResponse<RoleOfMenuDTO> getById(RoleOfMenuGetQry qry) {
        return roleOfMenuGetQryExe.execute(qry);
    }

    
    public MultiResponse<RoleOfMenuDTO> list(RoleOfMenuListQry qry) {
        return roleOfMenuListQryExe.execute(qry);
    }
    
    public Response delete(RoleOfMenuDeleteCmd roleOfMenuDeleteCmd) {
        return roleOfMenuDeleteCmdExe.execute(roleOfMenuDeleteCmd);
    }

    
    public PageResponse<RoleOfMenuDTO> page(RoleOfMenuPageQry roleOfMenuPageQry) {
        return roleOfMenuPageQryExe.execute(roleOfMenuPageQry);
    }

}
