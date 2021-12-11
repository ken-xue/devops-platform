package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.cicd.domain.domain.sys.UserOfRole;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.UserOfRoleDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-12-03 17:56:55
 */
@Component
public class UserOfRole2DOConvector implements Convector<UserOfRole, UserOfRoleDO>{
    
    public UserOfRoleDO toDO(UserOfRole userOfRole) {
        return UserOfRole2DOMapStruct.INSTANCE.toDO(userOfRole);
    }

    public UserOfRole toDomain(UserOfRoleDO userOfRoleDO) {
        return UserOfRole2DOMapStruct.INSTANCE.toDomain(userOfRoleDO);
    }

    @Override
    public List<UserOfRoleDO> toDOList(List<UserOfRole> userOfRoleList) {
        return UserOfRole2DOMapStruct.INSTANCE.toDOList(userOfRoleList);
    }

    @Override
    public List<UserOfRole> toDomainList(List<UserOfRoleDO> userOfRoleDOList) {
        return UserOfRole2DOMapStruct.INSTANCE.toDomainList(userOfRoleDOList);
    }
}
