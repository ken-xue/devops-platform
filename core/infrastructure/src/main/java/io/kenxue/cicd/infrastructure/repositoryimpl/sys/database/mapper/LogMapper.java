package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.LogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@Mapper
public interface LogMapper extends BaseMapper<LogDO> {

}
