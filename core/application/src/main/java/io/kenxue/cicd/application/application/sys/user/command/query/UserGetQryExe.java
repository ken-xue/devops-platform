package io.kenxue.cicd.application.application.sys.user.command.query;


import io.kenxue.cicd.application.application.sys.role.assembler.Role2DTOAssembler;
import io.kenxue.cicd.application.application.sys.user.assembler.User2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.sys.user.UserGetQry;
import io.kenxue.cicd.coreclient.dto.sys.user.UserDTO;
import io.kenxue.cicd.domain.domain.sys.Role;
import io.kenxue.cicd.domain.domain.sys.User;
import io.kenxue.cicd.domain.repository.sys.UserRepository;
import io.kenxue.cicd.domain.repository.sys.UserOfRoleRepository;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserGetQryExe {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserOfRoleRepository userOfRoleRepository;
    @Resource
    private User2DTOAssembler user2DTOAssembler;
    @Resource
    private Role2DTOAssembler role2DTOAssembler;

    public SingleResponse<UserDTO> execute(UserGetQry qry) {
        User user = userRepository.getById(qry.getId());
        //查询所属角色
        Set<Role> roles = userOfRoleRepository.getByUserId(user.getUuid());
        List<String> roleUuids = roles.stream().map(v -> v.getUuid()).collect(Collectors.toList());
        UserDTO userDTO = user2DTOAssembler.toDTO(user);
        userDTO.setRoleIdList(roleUuids);
        userDTO.setRoles(roles.stream().map(v->role2DTOAssembler.toDTO(v)).collect(Collectors.toSet()));
        return SingleResponse.of(userDTO);
    }

}
