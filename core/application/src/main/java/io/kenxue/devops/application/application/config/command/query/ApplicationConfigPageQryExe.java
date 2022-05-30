package io.kenxue.devops.application.application.config.command.query;

import io.kenxue.devops.application.application.config.assembler.ApplicationConfig2DTOAssembler;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigDTO;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.application.ApplicationConfigRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.application.ApplicationConfig;
import javax.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationConfigPageQryExe {

    @Resource
    private ApplicationConfigRepository applicationConfigRepository;
    @Resource
    private ApplicationConfig2DTOAssembler applicationConfig2DTOAssembler;

    public PageResponse<ApplicationConfigDTO> execute(ApplicationConfigPageQry qry) {
        Page<ApplicationConfig> page = applicationConfigRepository.page(qry);
        return PageResponse.of(applicationConfig2DTOAssembler.toDTOPage(page));
    }
}
