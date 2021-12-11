package io.kenxue.cicd.application.application.sys.roleofmenu.command.query;


import io.kenxue.cicd.application.application.sys.roleofmenu.assembler.RoleOfMenu2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.sys.roleofmenu.RoleOfMenuDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.sys.roleofmenu.RoleOfMenuGetQry;
import io.kenxue.cicd.domain.repository.sys.RoleOfMenuRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Component
public class RoleOfMenuGetQryExe {

    @Resource
    private RoleOfMenuRepository roleOfMenuRepository;
    @Resource
    private RoleOfMenu2DTOAssembler roleOfMenu2DTOAssembler;

    public SingleResponse<RoleOfMenuDTO> execute(RoleOfMenuGetQry qry) {
        return SingleResponse.of(roleOfMenu2DTOAssembler.toDTO(roleOfMenuRepository.getById(qry.getId())));
    }

}
