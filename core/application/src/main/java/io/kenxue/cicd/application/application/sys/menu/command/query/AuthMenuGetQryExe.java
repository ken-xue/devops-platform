package io.kenxue.cicd.application.application.sys.menu.command.query;


import io.kenxue.cicd.domain.domain.sys.Menu;
import io.kenxue.cicd.domain.domain.sys.Role;
import io.kenxue.cicd.domain.domain.sys.User;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.sys.menu.AuthMenuGetQry;
import io.kenxue.cicd.domain.repository.sys.RoleOfMenuRepository;
import io.kenxue.cicd.domain.repository.sys.UserRepository;
import io.kenxue.cicd.domain.repository.sys.UserOfRoleRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Component
public class AuthMenuGetQryExe {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserOfRoleRepository userOfRoleRepository;
    @Resource
    private RoleOfMenuRepository roleOfMenuRepository;

    public SingleResponse<Set<Menu>> execute(AuthMenuGetQry qry) {
        User user = userRepository.getByUserId(qry.getUserId());
        Assert.notNull(user.getUuid(),"用户UUID不能为空");
        //查询对应的角色
        Set<Role> roles = userOfRoleRepository.getByUserId(user.getUuid());
        //查询角色关联的菜单权限
        Set<Menu> menus = roleOfMenuRepository.getMenuByRoleUuids(roles.stream().map(v->v.getUuid()).collect(Collectors.toList()));
        return SingleResponse.of(menus);
    }

}
