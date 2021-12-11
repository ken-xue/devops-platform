package io.kenxue.cicd.application.application.sys.roleofmenu.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.domain.domain.sys.RoleOfMenu;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.roleofmenu.RoleOfMenuDeleteCmd;
import io.kenxue.cicd.domain.repository.sys.RoleOfMenuRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Component
public class RoleOfMenuDeleteCmdExe {
    @Resource
    private RoleOfMenuRepository roleOfMenuRepository;

    public Response execute(RoleOfMenuDeleteCmd cmd) {
        RoleOfMenu roleOfMenu = roleOfMenuRepository.getById(cmd.getId());
        roleOfMenu.deleted(UserThreadContext.get());
        roleOfMenuRepository.update(roleOfMenu);
        return Response.success();
    }
}
