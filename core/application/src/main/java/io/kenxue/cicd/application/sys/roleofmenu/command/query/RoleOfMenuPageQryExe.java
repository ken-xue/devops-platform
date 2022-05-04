package io.kenxue.cicd.application.sys.roleofmenu.command.query;

import io.kenxue.cicd.application.sys.roleofmenu.assembler.RoleOfMenu2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.sys.roleofmenu.RoleOfMenuDTO;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.sys.roleofmenu.RoleOfMenuPageQry;
import io.kenxue.cicd.domain.domain.sys.RoleOfMenu;
import io.kenxue.cicd.domain.repository.sys.RoleOfMenuRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Component
public class RoleOfMenuPageQryExe {

    @Resource
    private RoleOfMenuRepository roleOfMenuRepository;
    @Resource
    private RoleOfMenu2DTOAssembler roleOfMenu2DTOAssembler;

    public PageResponse<RoleOfMenuDTO> execute(RoleOfMenuPageQry qry) {
        Page<RoleOfMenu> page = roleOfMenuRepository.page(qry);
        return PageResponse.of(roleOfMenu2DTOAssembler.toDTOPage(page));
    }

}

