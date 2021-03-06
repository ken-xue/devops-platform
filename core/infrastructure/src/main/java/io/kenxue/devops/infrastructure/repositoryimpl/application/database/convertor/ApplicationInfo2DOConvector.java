package io.kenxue.devops.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.devops.domain.domain.application.ApplicationInfo;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject.ApplicationInfoDO;
import org.springframework.stereotype.Component;
import io.kenxue.devops.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationInfo2DOConvector implements Convector<ApplicationInfo,ApplicationInfoDO>{
    
    public ApplicationInfoDO toDO(ApplicationInfo applicationInfo) {
        return ApplicationInfo2DOMapStruct.INSTANCE.toDO(applicationInfo);
    }

    public ApplicationInfo toDomain(ApplicationInfoDO applicationInfoDO) {
        return ApplicationInfo2DOMapStruct.INSTANCE.toDomain(applicationInfoDO);
    }

    public List<ApplicationInfoDO> toDOList(List<ApplicationInfo> applicationInfoList) {
        return ApplicationInfo2DOMapStruct.INSTANCE.toDOList(applicationInfoList);
    }

    public List<ApplicationInfo> toDomainList(List<ApplicationInfoDO> applicationInfoDOList) {
        return ApplicationInfo2DOMapStruct.INSTANCE.toDomainList(applicationInfoDOList);
    }
}
