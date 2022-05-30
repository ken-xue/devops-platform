package io.kenxue.devops.application.sys.role.service;
import io.kenxue.devops.application.sys.role.command.RoleAddCmdExe;
import io.kenxue.devops.application.sys.role.command.RoleDeleteCmdExe;
import io.kenxue.devops.application.sys.role.command.RoleUpdateCmdExe;
import io.kenxue.devops.application.sys.role.command.query.RoleGetQryExe;
import io.kenxue.devops.application.sys.role.command.query.RoleListQryExe;
import io.kenxue.devops.application.sys.role.command.query.RolePageQryExe;
import io.kenxue.devops.coreclient.api.sys.RoleAppService;
import io.kenxue.devops.coreclient.dto.sys.role.*;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Service
public class RoleAppAppServiceImpl implements RoleAppService {
    @Resource
    private RoleAddCmdExe roleAddCmdExe;
    @Resource
    private RoleUpdateCmdExe roleUpdateCmdExe;
    @Resource
    private RoleGetQryExe roleGetQryExe;
    @Resource
    private RoleListQryExe roleListQryExe;
    @Resource
    private RolePageQryExe rolePageQryExe;
    @Resource
    private RoleDeleteCmdExe roleDeleteCmdExe;

    
    public Response add(RoleAddCmd roleAddCmd) {
        return  roleAddCmdExe.execute(roleAddCmd);
    }

    
    public Response update(RoleUpdateCmd cmd) {
        return roleUpdateCmdExe.execute(cmd);
    }

    
    public SingleResponse<RoleDTO> getById(RoleGetQry qry) {
        return roleGetQryExe.execute(qry);
    }

    
    public MultiResponse<RoleDTO> list(RoleListQry qry) {
        return roleListQryExe.execute(qry);
    }
    
    public Response delete(RoleDeleteCmd roleDeleteCmd) {
        return roleDeleteCmdExe.execute(roleDeleteCmd);
    }

    
    public PageResponse<RoleDTO> page(RolePageQry rolePageQry) {
        return rolePageQryExe.execute(rolePageQry);
    }

}
