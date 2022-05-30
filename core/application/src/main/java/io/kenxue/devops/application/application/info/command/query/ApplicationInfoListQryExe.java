package io.kenxue.devops.application.application.info.command.query;

import io.kenxue.devops.application.application.info.assembler.ApplicationInfo2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.ApplicationInfoDTO;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.ApplicationInfoListQry;
import io.kenxue.devops.domain.domain.application.ApplicationInfo;
import io.kenxue.devops.domain.repository.application.ApplicationInfoRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
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
