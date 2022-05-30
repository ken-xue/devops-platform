package io.kenxue.devops.infrastructure.repositoryimpl.application.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject.ApplicationOfUserDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Mapper
public interface ApplicationOfUserMapper extends BaseMapper<ApplicationOfUserDO> {

}
