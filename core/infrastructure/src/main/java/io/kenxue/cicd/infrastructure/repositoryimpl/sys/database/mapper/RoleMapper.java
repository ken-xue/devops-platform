package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.RoleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {

}
