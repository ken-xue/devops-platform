package io.kenxue.devops.application.sys.role.command.query;

import io.kenxue.devops.application.sys.role.assembler.Role2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.sys.role.RoleDTO;
import io.kenxue.devops.coreclient.dto.sys.role.RolePageQry;
import io.kenxue.devops.domain.domain.sys.Role;
import io.kenxue.devops.domain.repository.sys.RoleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Component
public class RolePageQryExe {

    @Resource
    private RoleRepository roleRepository;
    @Resource
    private Role2DTOAssembler role2DTOAssembler;

    public PageResponse<RoleDTO> execute(RolePageQry qry) {
        Page<Role> page = roleRepository.page(qry);
        return PageResponse.of(role2DTOAssembler.toDTOPage(page));
    }

}

