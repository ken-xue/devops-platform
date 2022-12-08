package io.kenxue.devops.application.sys.userofrole.command.query;

import io.kenxue.devops.application.sys.userofrole.assembler.UserOfRole2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.sys.userofrole.UserOfRoleDTO;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.sys.userofrole.UserOfRolePageQry;
import io.kenxue.devops.domain.domain.sys.UserOfRole;
import io.kenxue.devops.domain.repository.sys.UserOfRoleRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Component
public class UserOfRolePageQryExe {

    @Resource
    private UserOfRoleRepository userOfRoleRepository;
    @Resource
    private UserOfRole2DTOAssembler userOfRole2DTOAssembler;

    public PageResponse<UserOfRoleDTO> execute(UserOfRolePageQry qry) {
        Page<UserOfRole> page = userOfRoleRepository.page(qry);
        return PageResponse.of(userOfRole2DTOAssembler.toDTOPage(page));
    }

}

