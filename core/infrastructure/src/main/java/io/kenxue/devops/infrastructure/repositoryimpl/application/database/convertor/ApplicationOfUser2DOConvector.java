package io.kenxue.devops.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.devops.domain.domain.application.ApplicationOfUser;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject.ApplicationOfUserDO;
import org.springframework.stereotype.Component;
import io.kenxue.devops.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
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
