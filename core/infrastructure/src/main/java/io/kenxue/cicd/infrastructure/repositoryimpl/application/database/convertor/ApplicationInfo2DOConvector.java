package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.cicd.domain.domain.application.ApplicationInfo;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Convector;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationInfoDO;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 17:17:54
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
