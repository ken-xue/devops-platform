package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.cicd.domain.domain.application.ApplicationConfig;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationConfigDO;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationConfig2DOConvector implements Convector<ApplicationConfig,ApplicationConfigDO>{
    
    public ApplicationConfigDO toDO(ApplicationConfig applicationConfig) {
        return ApplicationConfig2DOMapStruct.INSTANCE.toDO(applicationConfig);
    }

    public ApplicationConfig toDomain(ApplicationConfigDO applicationConfigDO) {
        return ApplicationConfig2DOMapStruct.INSTANCE.toDomain(applicationConfigDO);
    }

    public List<ApplicationConfigDO> toDOList(List<ApplicationConfig> applicationConfigList) {
        return ApplicationConfig2DOMapStruct.INSTANCE.toDOList(applicationConfigList);
    }

    public List<ApplicationConfig> toDomainList(List<ApplicationConfigDO> applicationConfigDOList) {
        return ApplicationConfig2DOMapStruct.INSTANCE.toDomainList(applicationConfigDOList);
    }
}
