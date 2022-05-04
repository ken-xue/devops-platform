package io.kenxue.cicd.infrastructure.repositoryimpl.jenkins.database.convertor;

import io.kenxue.cicd.domain.domain.jenkins.Jenkins;
import io.kenxue.cicd.infrastructure.repositoryimpl.jenkins.database.dataobject.JenkinsDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Jenkins2DOMapStruct {

    Jenkins2DOMapStruct INSTANCE = Mappers.getMapper(Jenkins2DOMapStruct.class);

    @Mappings({})
    JenkinsDO toDO(Jenkins jenkins);

    @Mappings({})
    Jenkins toDomain(JenkinsDO jenkinsDO);

    List<JenkinsDO> toDOList(List<Jenkins> jenkinsList);

    List<Jenkins> toDomainList(List<JenkinsDO> jenkinsDOList);
}
