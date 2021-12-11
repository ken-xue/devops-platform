package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.cicd.domain.domain.sys.Role;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.RoleDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色表
 * @author mikey
 * @date 2021-12-03 17:27:04
 */
@Component
public class Role2DOConvector implements Convector<Role,RoleDO>{
    
    public RoleDO toDO(Role role) {
        return Role2DOMapStruct.INSTANCE.toDO(role);
    }

    public Role toDomain(RoleDO dO) {
        return Role2DOMapStruct.INSTANCE.toDomain(dO);
    }

    @Override
    public List<RoleDO> toDOList(List<Role> roleList) {
        return Role2DOMapStruct.INSTANCE.toDOList(roleList);
    }

    @Override
    public List<Role> toDomainList(List<RoleDO> roleDOList) {
        return Role2DOMapStruct.INSTANCE.toDomainList(roleDOList);
    }
}
