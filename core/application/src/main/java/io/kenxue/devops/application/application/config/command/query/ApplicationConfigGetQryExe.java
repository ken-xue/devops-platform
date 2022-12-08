package io.kenxue.devops.application.application.config.command.query;

import io.kenxue.devops.application.application.config.assembler.ApplicationConfig2DTOAssembler;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigGetQry;
import io.kenxue.devops.domain.repository.application.ApplicationConfigRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationConfigGetQryExe {

    @Resource
    private ApplicationConfigRepository applicationConfigRepository;
    @Resource
    private ApplicationConfig2DTOAssembler applicationConfig2DTOAssembler;

    public SingleResponse<ApplicationConfigDTO> execute(ApplicationConfigGetQry qry) {
        return SingleResponse.of(applicationConfig2DTOAssembler.toDTO(applicationConfigRepository.getById(qry.getId())));
    }

}
