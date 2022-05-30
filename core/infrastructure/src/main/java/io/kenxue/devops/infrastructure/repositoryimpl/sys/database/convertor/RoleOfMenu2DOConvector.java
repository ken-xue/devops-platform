package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.devops.domain.domain.sys.RoleOfMenu;
import io.kenxue.devops.infrastructure.common.convector.Convector;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.RoleOfMenuDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-12-03 17:39:58
 */
@Component
public class RoleOfMenu2DOConvector implements Convector<RoleOfMenu, RoleOfMenuDO> {
    
    public RoleOfMenuDO toDO(RoleOfMenu roleOfMenu) {
        return RoleOfMenu2DOMapStruct.INSTANCE.toDO(roleOfMenu);
    }

    public RoleOfMenu toDomain(RoleOfMenuDO roleOfMenuDO) {
        return RoleOfMenu2DOMapStruct.INSTANCE.toDomain(roleOfMenuDO);
    }

    @Override
    public List<RoleOfMenuDO> toDOList(List<RoleOfMenu> roleOfMenuList) {
        return RoleOfMenu2DOMapStruct.INSTANCE.toDOList(roleOfMenuList);
    }

    @Override
    public List<RoleOfMenu> toDomainList(List<RoleOfMenuDO> roleOfMenuDOList) {
        return RoleOfMenu2DOMapStruct.INSTANCE.toDomainList(roleOfMenuDOList);
    }
}
