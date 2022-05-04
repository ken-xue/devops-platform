package io.kenxue.cicd.application.sys.user.command.query;


import io.kenxue.cicd.application.sys.role.assembler.Role2DTOAssembler;
import io.kenxue.cicd.application.sys.user.assembler.User2DTOAssembler;
import io.kenxue.cicd.domain.domain.sys.Role;
import io.kenxue.cicd.domain.domain.sys.User;
import io.kenxue.cicd.coreclient.dto.sys.user.UserDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.sys.user.UserFullGetQry;
import io.kenxue.cicd.domain.repository.sys.UserRepository;
import io.kenxue.cicd.domain.repository.sys.UserOfRoleRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserInfoGetQryExe {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserOfRoleRepository userOfRoleRepository;
    @Resource
    private User2DTOAssembler user2DTOAssembler;
    @Resource
    private Role2DTOAssembler role2DTOAssembler;

    public SingleResponse<UserDTO> execute(UserFullGetQry qry) {
        //查询用户信息
        User user = userRepository.getByUserId(qry.getUserId());
        Assert.notNull(user.getUuid(),"查询用户信息失败");
        //查询对应的角色
        Set<Role> roles = userOfRoleRepository.getByUserId(user.getUuid());
        UserDTO userDTO = user2DTOAssembler.toDTO(user);
        userDTO.setRoles(roles.stream().map(v->role2DTOAssembler.toDTO(v)).collect(Collectors.toSet()));
        return SingleResponse.of(userDTO);
    }

}
