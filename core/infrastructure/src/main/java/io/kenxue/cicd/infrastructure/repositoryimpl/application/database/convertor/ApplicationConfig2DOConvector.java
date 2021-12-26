package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor;

import io.kenxue.cicd.domain.domain.application.ApplicationConfig;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationConfigDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Convector;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Component
public class ApplicationConfig2DOConvector implements Convector<ApplicationConfig,ApplicationConfigDO> {
    
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
