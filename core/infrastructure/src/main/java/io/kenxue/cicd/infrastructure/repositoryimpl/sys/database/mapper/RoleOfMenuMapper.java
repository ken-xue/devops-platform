package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.RoleOfMenuDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Mapper
public interface RoleOfMenuMapper extends BaseMapper<RoleOfMenuDO> {

}
