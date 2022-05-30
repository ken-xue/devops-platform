package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.UserOfRoleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Mapper
public interface UserOfRoleMapper extends BaseMapper<UserOfRoleDO> {

}
