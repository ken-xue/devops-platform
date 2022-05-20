package io.kenxue.cicd.infrastructure.repositoryimpl.middleware.jenkins.database.convertor;

import io.kenxue.cicd.domain.domain.middleware.Jenkins;
import io.kenxue.cicd.infrastructure.repositoryimpl.middleware.jenkins.database.dataobject.JenkinsDO;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Component
public class Jenkins2DOConvector implements Convector<Jenkins,JenkinsDO>{
    
    public JenkinsDO toDO(Jenkins jenkins) {
        return Jenkins2DOMapStruct.INSTANCE.toDO(jenkins);
    }

    public Jenkins toDomain(JenkinsDO jenkinsDO) {
        return Jenkins2DOMapStruct.INSTANCE.toDomain(jenkinsDO);
    }

    public List<JenkinsDO> toDOList(List<Jenkins> jenkinsList) {
        return Jenkins2DOMapStruct.INSTANCE.toDOList(jenkinsList);
    }

    public List<Jenkins> toDomainList(List<JenkinsDO> jenkinsDOList) {
        return Jenkins2DOMapStruct.INSTANCE.toDomainList(jenkinsDOList);
    }
}
