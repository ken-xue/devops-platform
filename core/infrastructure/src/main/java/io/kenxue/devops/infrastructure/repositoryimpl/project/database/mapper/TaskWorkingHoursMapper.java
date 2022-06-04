package io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskWorkingHoursDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Mapper
public interface TaskWorkingHoursMapper extends BaseMapper<TaskWorkingHoursDO> {

}
