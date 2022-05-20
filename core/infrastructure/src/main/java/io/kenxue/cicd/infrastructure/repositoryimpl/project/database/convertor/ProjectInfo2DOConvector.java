package io.kenxue.cicd.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.cicd.domain.domain.project.ProjectInfo;
import io.kenxue.cicd.infrastructure.repositoryimpl.project.database.dataobject.ProjectInfoDO;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectInfo2DOConvector implements Convector<ProjectInfo,ProjectInfoDO>{
    
    public ProjectInfoDO toDO(ProjectInfo projectInfo) {
        return ProjectInfo2DOMapStruct.INSTANCE.toDO(projectInfo);
    }

    public ProjectInfo toDomain(ProjectInfoDO projectInfoDO) {
        return ProjectInfo2DOMapStruct.INSTANCE.toDomain(projectInfoDO);
    }

    public List<ProjectInfoDO> toDOList(List<ProjectInfo> projectInfoList) {
        return ProjectInfo2DOMapStruct.INSTANCE.toDOList(projectInfoList);
    }

    public List<ProjectInfo> toDomainList(List<ProjectInfoDO> projectInfoDOList) {
        return ProjectInfo2DOMapStruct.INSTANCE.toDomainList(projectInfoDOList);
    }
}
