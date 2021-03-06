package io.kenxue.devops.application.middleware.jenkins.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.middleware.jenkins.JenkinsDTO;
import io.kenxue.devops.domain.domain.middleware.Jenkins;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Component
public class Jenkins2DTOAssembler implements Assembler<JenkinsDTO, Jenkins> {

    @Override
    public JenkinsDTO toDTO(Jenkins jenkins) {
        return Jenkins2DTOMapStruct.INSTANCE.toDTO(jenkins);
    }

    @Override
    public Jenkins toDomain(JenkinsDTO jenkinsDTO) {
        return Jenkins2DTOMapStruct.INSTANCE.toDomain(jenkinsDTO);
    }

    @Override
    public List<JenkinsDTO> toDTOList(List<Jenkins> jenkinsList) {
        return Jenkins2DTOMapStruct.INSTANCE.toDTOList(jenkinsList);
    }

    @Override
    public List<Jenkins> toDomainList(List<JenkinsDTO> jenkinsDTOList) {
        return Jenkins2DTOMapStruct.INSTANCE.toDomainList(jenkinsDTOList);
    }

    @Override
    public Page<JenkinsDTO> toDTOPage(Page<Jenkins> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), Jenkins2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
