package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.devops.infrastructure.common.convector.Convector;
import io.kenxue.devops.domain.domain.sys.Config;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.ConfigDO;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
@Component
public class Config2DOConvector implements Convector<Config,ConfigDO>{
    
    public ConfigDO toDO(Config config) {
        return Config2DOMapStruct.INSTANCE.toDO(config);
    }

    public Config toDomain(ConfigDO configDO) {
        return Config2DOMapStruct.INSTANCE.toDomain(configDO);
    }

    public List<ConfigDO> toDOList(List<Config> configList) {
        return Config2DOMapStruct.INSTANCE.toDOList(configList);
    }

    public List<Config> toDomainList(List<ConfigDO> configDOList) {
        return Config2DOMapStruct.INSTANCE.toDomainList(configDOList);
    }
}
