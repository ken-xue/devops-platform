package io.kenxue.cicd.application.application.application.applicationconfig.command.query;

import io.kenxue.cicd.application.application.application.applicationconfig.assembler.ApplicationConfig2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.application.applicationconfig.ApplicationConfigDTO;
import io.kenxue.cicd.coreclient.dto.application.applicationconfig.ApplicationConfigListQry;
import io.kenxue.cicd.domain.domain.application.ApplicationConfig;
import io.kenxue.cicd.domain.repository.application.ApplicationConfigRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
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
