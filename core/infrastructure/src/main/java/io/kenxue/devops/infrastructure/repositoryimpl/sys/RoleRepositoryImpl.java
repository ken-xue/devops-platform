package io.kenxue.devops.infrastructure.repositoryimpl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.sys.role.RolePageQry;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor.Role2DOConvector;
import io.kenxue.devops.domain.domain.sys.Role;
import io.kenxue.devops.domain.repository.sys.RoleRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.RoleDO;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Component
@Slf4j
public class RoleRepositoryImpl implements RoleRepository {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private Role2DOConvector role2DOConvector;

    public void create(Role role){
        roleMapper.insert(role2DOConvector.toDO(role));
    }

    public void update(Role role){
        roleMapper.updateById(role2DOConvector.toDO(role));
    }
    
    public Role getById(Long id){
        RoleDO roleDO = roleMapper.selectById(id);
        return role2DOConvector.toDomain(roleDO);
    }

    @Override
    public Page<Role> page(RolePageQry qry) {
        QueryWrapper<RoleDO> wp = new QueryWrapper<>();
        if (StringUtils.isNotBlank(qry.getRoleDTO().getRoleName())){
            wp.like("role_name",qry.getRoleDTO().getRoleName());
        }
        wp.eq("deleted",0);//未删除
        IPage page = this.roleMapper.selectPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page(qry.getPageIndex(),qry.getPageSize()), wp);
        return Page.of(page.getCurrent(),page.getSize(),page.getTotal(),role2DOConvector.toDomainList(page.getRecords()));
    }

    @Override
    public List<Role> list(String roleName) {
        QueryWrapper qw = new QueryWrapper<RoleDO>();
        if (StringUtils.isNotBlank(roleName))qw.eq("role_name",roleName);
        return role2DOConvector.toDomainList(roleMapper.selectList(qw));
    }
}
