package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.UserOfDeptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户关联部门表
 * @author mikey
 * @date 2021-11-21 09:20:42
 */
@Mapper
public interface UserOfDeptMapper extends BaseMapper<UserOfDeptDO> {

}
