package io.kenxue.cicd.application.application.sys.role.command.query;

import io.kenxue.cicd.application.application.sys.role.assembler.Role2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.sys.role.RoleDTO;
import io.kenxue.cicd.coreclient.dto.sys.role.RoleListQry;
import io.kenxue.cicd.domain.domain.sys.Role;
import io.kenxue.cicd.domain.repository.sys.RoleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Component
public class RoleListQryExe {

    @Resource
    private RoleRepository roleRepository;
    @Resource
    private Role2DTOAssembler role2DTOAssembler;

    public MultiResponse<RoleDTO> execute(RoleListQry qry) {
        List<Role> list = roleRepository.list(qry.getRoleName());
        return MultiResponse.of(role2DTOAssembler.toDTOList(list));
    }

}

