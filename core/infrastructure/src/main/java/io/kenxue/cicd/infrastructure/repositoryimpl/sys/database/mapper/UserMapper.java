package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper
 *
 * @date 2019-02-27 5:03 PM
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

}
