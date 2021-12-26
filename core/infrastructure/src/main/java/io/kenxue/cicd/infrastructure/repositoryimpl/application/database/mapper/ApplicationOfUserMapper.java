package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationOfUserDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Mapper
public interface ApplicationOfUserMapper extends BaseMapper<ApplicationOfUserDO> {

}
