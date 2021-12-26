package io.kenxue.cicd.acl.authorize.impl;

import io.kenxue.cicd.domain.domain.sys.Menu;
import io.kenxue.cicd.domain.domain.sys.Role;
import io.kenxue.cicd.domain.repository.sys.RoleOfMenuRepository;
import io.kenxue.cicd.domain.repository.sys.UserRepository;
import io.kenxue.cicd.domain.domain.sys.User;
import io.kenxue.cicd.domain.repository.sys.UserOfRoleRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserOfRoleRepository userOfRoleRepository;
    @Resource
    private RoleOfMenuRepository roleOfMenuRepository;

    
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.getByUserId(userId);
        if(user == null){
            throw new UsernameNotFoundException("账号不存在:"+userId);
        }

        Collection authorities = loadUserAuthorities(user);//权限
        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getUserPassword(), authorities);
    }

    private Collection loadUserAuthorities(User user) {
        ArrayList<GrantedAuthorityImpl> grantedAuthorities = new ArrayList<>(2<<4);
        //查询用户信息
        user = userRepository.getByUserId(user.getUserId());
        Assert.notNull(user.getUuid(),"查询用户信息失败");
        //查询对应的角色
        Set<Role> roles = userOfRoleRepository.getByUserId(user.getUuid());
        //查询角色关联的菜单权限
        Set<Menu> menus = roleOfMenuRepository.getMenuByRoleUuids(roles.stream().map(v->v.getUuid()).collect(Collectors.toList()));
        for (Menu v:menus){
            String menuPerms = v.getMenuPerms();
            if (StringUtils.isNotBlank(menuPerms)) {
                String[] perms = menuPerms.split(",");
                for (int i = 0; i < perms.length; i++) {
                    String perm = perms[i];
                    if (StringUtils.isNotBlank(perm))
                        grantedAuthorities.add(new GrantedAuthorityImpl(perm));
                }
            }
        }
        return grantedAuthorities;
    }

}
