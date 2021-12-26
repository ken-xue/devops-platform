package io.kenxue.cicd.application.application.application.applicationinfo.command.query;

import io.kenxue.cicd.application.application.application.applicationinfo.assembler.ApplicationInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.application.applicationinfo.ApplicationInfoDTO;
import io.kenxue.cicd.coreclient.dto.application.applicationinfo.ApplicationInfoListQry;
import io.kenxue.cicd.domain.domain.application.ApplicationInfo;
import io.kenxue.cicd.domain.repository.application.ApplicationInfoRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Component
public class ApplicationInfoListQryExe {
    @Resource
    private ApplicationInfoRepository applicationInfoRepository;
    @Resource
    private ApplicationInfo2DTOAssembler applicationInfo2DTOAssembler;

    public MultiResponse<ApplicationInfoDTO> execute(ApplicationInfoListQry qry) {
        List<ApplicationInfo> applicationInfoDTOList = applicationInfoRepository.list(qry);
        return MultiResponse.of(applicationInfo2DTOAssembler.toDTOList(applicationInfoDTOList));
    }
}
