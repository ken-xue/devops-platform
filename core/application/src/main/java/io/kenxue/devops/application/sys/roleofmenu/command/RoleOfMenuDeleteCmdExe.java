package io.kenxue.devops.application.sys.roleofmenu.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.domain.domain.sys.RoleOfMenu;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.roleofmenu.RoleOfMenuDeleteCmd;
import io.kenxue.devops.domain.repository.sys.RoleOfMenuRepository;
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
