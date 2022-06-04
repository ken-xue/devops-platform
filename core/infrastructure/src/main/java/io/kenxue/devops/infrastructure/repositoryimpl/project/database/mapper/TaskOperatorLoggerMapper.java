package io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskOperatorLoggerDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Mapper
public interface TaskOperatorLoggerMapper extends BaseMapper<TaskOperatorLoggerDO> {

}
