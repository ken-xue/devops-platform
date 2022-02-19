package io.kenxue.cicd.infrastructure.repositoryimpl.project.database.convertor;

import io.kenxue.cicd.domain.domain.project.ProjectOfUser;
import io.kenxue.cicd.infrastructure.repositoryimpl.project.database.dataobject.ProjectOfUserDO;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Convector;
import java.util.List;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Component
public class ProjectOfUser2DOConvector implements Convector<ProjectOfUser,ProjectOfUserDO>{
    
    public ProjectOfUserDO toDO(ProjectOfUser projectOfUser) {
        return ProjectOfUser2DOMapStruct.INSTANCE.toDO(projectOfUser);
    }

    public ProjectOfUser toDomain(ProjectOfUserDO projectOfUserDO) {
        return ProjectOfUser2DOMapStruct.INSTANCE.toDomain(projectOfUserDO);
    }

    public List<ProjectOfUserDO> toDOList(List<ProjectOfUser> projectOfUserList) {
        return ProjectOfUser2DOMapStruct.INSTANCE.toDOList(projectOfUserList);
    }

    public List<ProjectOfUser> toDomainList(List<ProjectOfUserDO> projectOfUserDOList) {
        return ProjectOfUser2DOMapStruct.INSTANCE.toDomainList(projectOfUserDOList);
    }
}
