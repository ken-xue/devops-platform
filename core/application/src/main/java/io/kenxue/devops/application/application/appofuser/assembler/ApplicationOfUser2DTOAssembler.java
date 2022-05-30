package io.kenxue.devops.application.application.appofuser.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.application.applicationofuser.ApplicationOfUserDTO;
import io.kenxue.devops.domain.domain.application.ApplicationOfUser;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationOfUser2DTOAssembler implements Assembler<ApplicationOfUserDTO, ApplicationOfUser> {

    @Override
    public ApplicationOfUserDTO toDTO(ApplicationOfUser applicationOfUser) {
        return ApplicationOfUser2DTOMapStruct.INSTANCE.toDTO(applicationOfUser);
    }

    @Override
    public ApplicationOfUser toDomain(ApplicationOfUserDTO applicationOfUserDTO) {
        return ApplicationOfUser2DTOMapStruct.INSTANCE.toDomain(applicationOfUserDTO);
    }

    @Override
    public List<ApplicationOfUserDTO> toDTOList(List<ApplicationOfUser> applicationOfUserList) {
        return ApplicationOfUser2DTOMapStruct.INSTANCE.toDTOList(applicationOfUserList);
    }

    @Override
    public List<ApplicationOfUser> toDomainList(List<ApplicationOfUserDTO> applicationOfUserDTOList) {
        return ApplicationOfUser2DTOMapStruct.INSTANCE.toDomainList(applicationOfUserDTOList);
    }

    @Override
    public Page<ApplicationOfUserDTO> toDTOPage(Page<ApplicationOfUser> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), ApplicationOfUser2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
