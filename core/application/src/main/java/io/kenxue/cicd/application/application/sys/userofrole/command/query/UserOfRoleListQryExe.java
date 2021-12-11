package io.kenxue.cicd.application.application.sys.userofrole.command.query;

import io.kenxue.cicd.application.application.sys.userofrole.assembler.UserOfRole2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.sys.userofrole.UserOfRoleDTO;
import io.kenxue.cicd.coreclient.dto.sys.userofrole.UserOfRoleListQry;
import io.kenxue.cicd.domain.repository.sys.UserOfRoleRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Component
public class UserOfRoleListQryExe {

    @Resource
    private UserOfRoleRepository userOfRoleRepository;
    @Resource
    private UserOfRole2DTOAssembler userOfRole2DTOAssembler;

    public MultiResponse<UserOfRoleDTO> execute(UserOfRoleListQry qry) {
        return MultiResponse.of(userOfRole2DTOAssembler.toDTOList(userOfRoleRepository.list(qry)));
    }

}

