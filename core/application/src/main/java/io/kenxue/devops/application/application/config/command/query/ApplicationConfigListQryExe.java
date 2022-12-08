package io.kenxue.devops.application.application.config.command.query;

import io.kenxue.devops.application.application.config.assembler.ApplicationConfig2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigDTO;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigListQry;
import io.kenxue.devops.domain.domain.application.ApplicationConfig;
import io.kenxue.devops.domain.repository.application.ApplicationConfigRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationConfigListQryExe {
    @Resource
    private ApplicationConfigRepository applicationConfigRepository;
    @Resource
    private ApplicationConfig2DTOAssembler applicationConfig2DTOAssembler;

    public MultiResponse<ApplicationConfigDTO> execute(ApplicationConfigListQry qry) {
        List<ApplicationConfig> applicationConfigDTOList = applicationConfigRepository.list(qry);
        return MultiResponse.of(applicationConfig2DTOAssembler.toDTOList(applicationConfigDTOList));
    }
}
