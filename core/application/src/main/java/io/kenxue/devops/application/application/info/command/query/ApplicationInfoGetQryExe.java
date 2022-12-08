package io.kenxue.devops.application.application.info.command.query;

import io.kenxue.devops.application.application.info.assembler.ApplicationInfo2DTOAssembler;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.ApplicationInfoDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.ApplicationInfoGetQry;
import io.kenxue.devops.domain.repository.application.ApplicationInfoRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationInfoGetQryExe {

    @Resource
    private ApplicationInfoRepository applicationInfoRepository;
    @Resource
    private ApplicationInfo2DTOAssembler applicationInfo2DTOAssembler;

    public SingleResponse<ApplicationInfoDTO> execute(ApplicationInfoGetQry qry) {
        return SingleResponse.of(applicationInfo2DTOAssembler.toDTO(applicationInfoRepository.getById(qry.getId())));
    }

}
