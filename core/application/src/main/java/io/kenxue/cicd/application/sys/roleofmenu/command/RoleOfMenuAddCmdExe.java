package io.kenxue.cicd.application.sys.roleofmenu.command;

import io.kenxue.cicd.application.sys.roleofmenu.assembler.RoleOfMenu2DTOAssembler;
import io.kenxue.cicd.domain.domain.sys.RoleOfMenu;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.roleofmenu.RoleOfMenuAddCmd;
import io.kenxue.cicd.domain.repository.sys.RoleOfMenuRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Component
public class RoleOfMenuAddCmdExe {

    @Resource
    private RoleOfMenuRepository roleOfMenuRepository;
    @Resource
    private RoleOfMenu2DTOAssembler roleOfMenu2DTOAssembler;

    public Response execute(RoleOfMenuAddCmd cmd) {
        RoleOfMenu roleOfMenu = roleOfMenu2DTOAssembler.toDomain(cmd.getRoleOfMenuDTO());
        roleOfMenuRepository.create(roleOfMenu);
        return Response.success();
    }
}
