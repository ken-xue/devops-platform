package io.kenxue.devops.application.middleware.jenkins.assembler;

import io.kenxue.devops.coreclient.dto.middleware.jenkins.JenkinsDTO;
import io.kenxue.devops.domain.domain.middleware.Jenkins;
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
public interface Jenkins2DTOMapStruct {

    Jenkins2DTOMapStruct INSTANCE = Mappers.getMapper(Jenkins2DTOMapStruct.class);

    @Mappings({})
    JenkinsDTO toDTO(Jenkins jenkins);

    @Mappings({})
    Jenkins toDomain(JenkinsDTO jenkinsDTO);

    List<JenkinsDTO> toDTOList(List<Jenkins> JenkinsList);

    List<Jenkins> toDomainList(List<JenkinsDTO> JenkinsDTOList);
}
