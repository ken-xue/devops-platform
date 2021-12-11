package io.kenxue.cicd.application.application.sys.userofrole.command.query;


import io.kenxue.cicd.application.application.sys.userofrole.assembler.UserOfRole2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.sys.userofrole.UserOfRoleDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.sys.userofrole.UserOfRoleGetQry;
import io.kenxue.cicd.domain.domain.sys.UserOfRole;
import io.kenxue.cicd.domain.repository.sys.UserOfRoleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Component
public class UserOfRoleGetQryExe {

    @Resource
    private UserOfRoleRepository userOfRoleRepository;
    @Resource
    private UserOfRole2DTOAssembler userOfRole2DTOAssembler;

    public SingleResponse<UserOfRoleDTO> execute(UserOfRoleGetQry qry) {
        UserOfRole userOfRole = userOfRoleRepository.getById(qry.getId());
        return SingleResponse.of(userOfRole2DTOAssembler.toDTO(userOfRole));
    }

}
