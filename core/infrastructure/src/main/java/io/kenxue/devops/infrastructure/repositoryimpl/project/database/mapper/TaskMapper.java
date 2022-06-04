package io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Mapper
public interface TaskMapper extends BaseMapper<TaskDO> {

}
