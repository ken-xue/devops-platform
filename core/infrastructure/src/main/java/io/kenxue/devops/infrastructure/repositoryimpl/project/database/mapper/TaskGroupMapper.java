package io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskGroupDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Mapper
public interface TaskGroupMapper extends BaseMapper<TaskGroupDO> {

}
