package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.cicd.domain.domain.application.ApplicationOfUser;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Convector;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationOfUserDO;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Component
public class ApplicationOfUser2DOConvector implements Convector<ApplicationOfUser,ApplicationOfUserDO>{
    
    public ApplicationOfUserDO toDO(ApplicationOfUser applicationOfUser) {
        return ApplicationOfUser2DOMapStruct.INSTANCE.toDO(applicationOfUser);
    }

    public ApplicationOfUser toDomain(ApplicationOfUserDO applicationOfUserDO) {
        return ApplicationOfUser2DOMapStruct.INSTANCE.toDomain(applicationOfUserDO);
    }

    public List<ApplicationOfUserDO> toDOList(List<ApplicationOfUser> applicationOfUserList) {
        return ApplicationOfUser2DOMapStruct.INSTANCE.toDOList(applicationOfUserList);
    }

    public List<ApplicationOfUser> toDomainList(List<ApplicationOfUserDO> applicationOfUserDOList) {
        return ApplicationOfUser2DOMapStruct.INSTANCE.toDomainList(applicationOfUserDOList);
    }
}
