package io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.ProjectOfUserDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Mapper
public interface ProjectOfUserMapper extends BaseMapper<ProjectOfUserDO> {

}
