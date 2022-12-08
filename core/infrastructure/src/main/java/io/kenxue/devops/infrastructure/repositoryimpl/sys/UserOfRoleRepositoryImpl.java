package io.kenxue.devops.infrastructure.repositoryimpl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.sys.userofrole.UserOfRoleListQry;
import io.kenxue.devops.coreclient.dto.sys.userofrole.UserOfRolePageQry;
import io.kenxue.devops.domain.domain.sys.Role;
import io.kenxue.devops.domain.factory.sys.UserOfRoleFactory;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor.Role2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor.UserOfRole2DOConvector;
import io.kenxue.devops.domain.domain.sys.UserOfRole;
import io.kenxue.devops.domain.repository.sys.UserOfRoleRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.RoleDO;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.UserOfRoleDO;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.mapper.RoleMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.mapper.UserOfRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import jakarta.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Component
@Slf4j
public class UserOfRoleRepositoryImpl implements UserOfRoleRepository {

    @Resource
    private UserOfRoleMapper userOfRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserOfRole2DOConvector userOfRole2DOConvector;
    @Resource
    private Role2DOConvector role2DOConvector;


    @Override
    public void create(UserOfRole userOfRole){
        userOfRoleMapper.insert(userOfRole2DOConvector.toDO(userOfRole));
    }

    @Override
    public void update(UserOfRole userOfRole){
        userOfRoleMapper.updateById(userOfRole2DOConvector.toDO(userOfRole));
    }

    @Override
    public UserOfRole getById(long id){
        return userOfRole2DOConvector.toDomain(userOfRoleMapper.selectById(id));
    }

    /**
     * 查询用户关联的角色
     * @param userUUID
     * @return
     */
    @Override
    public Set<Role> getByUserId(String userUUID) {
        Set<Role> roles = new HashSet<>(1<<4);
        QueryWrapper<UserOfRoleDO> qw = new QueryWrapper<>();
        qw.eq("user_uuid",userUUID);
        List<UserOfRoleDO> userOfRoleDOS = userOfRoleMapper.selectList(qw);
        if (CollectionUtils.isEmpty(userOfRoleDOS))return roles;
        //查询对应的角色
        QueryWrapper<RoleDO> rqw = new QueryWrapper<>();
        rqw.in("uuid",userOfRoleDOS.stream().map(v->v.getRoleUuid()).collect(Collectors.toList()));
        List<RoleDO> roleDOS = roleMapper.selectList(rqw);
        roleDOS.forEach(v-> roles.add(role2DOConvector.toDomain(v)));
        return roles;
    }


    @Override
    public void removeByUserUuid(String uuid) {
        UpdateWrapper<UserOfRoleDO> wrapper = new UpdateWrapper<UserOfRoleDO>().eq("user_uuid", uuid);
        userOfRoleMapper.delete(wrapper);
    }


    @Override
    public void save(List<String> roleIdList, String uuid) {
        roleIdList.forEach(v->{
            UserOfRole userOfRole = UserOfRoleFactory.getUserOfRole();
            userOfRole.setUserUuid(uuid);
            userOfRole.setRoleUuid(v);
            userOfRole.create(UserThreadContext.get());
            userOfRole.validate();
            userOfRoleMapper.insert(userOfRole2DOConvector.toDO(userOfRole));
        });
    }

    @Override
    public List<UserOfRole> list(UserOfRoleListQry qry) {
        return userOfRole2DOConvector.toDomainList(userOfRoleMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<UserOfRole> page(UserOfRolePageQry qry) {
        QueryWrapper<UserOfRoleDO> wp = new QueryWrapper<>();
        wp.eq("deleted",0);//未删除
        IPage doPage = new PageDTO(qry.getPageIndex(), qry.getPageSize());
        IPage<UserOfRoleDO> page = userOfRoleMapper.selectPage(doPage, wp);
        return Page.of(page.getCurrent(),page.getSize(),page.getTotal(),userOfRole2DOConvector.toDomainList(page.getRecords()));
    }
}
