package io.kenxue.devops.infrastructure.repositoryimpl.project.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.domain.domain.project.ProjectInfo;
import io.kenxue.devops.infrastructure.repositoryimpl.project.database.dataobject.ProjectInfoDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Mapper
public interface ProjectInfoMapper extends BaseMapper<ProjectInfoDO> {
    IPage<ProjectInfoDO> queryRelatedProject(IPage<ProjectInfo> page, String uuid,String username,String projectName);
}
