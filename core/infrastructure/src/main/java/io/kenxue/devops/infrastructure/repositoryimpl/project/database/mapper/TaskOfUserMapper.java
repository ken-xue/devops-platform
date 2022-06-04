package io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.TaskOfUserDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Mapper
public interface TaskOfUserMapper extends BaseMapper<TaskOfUserDO> {

}
