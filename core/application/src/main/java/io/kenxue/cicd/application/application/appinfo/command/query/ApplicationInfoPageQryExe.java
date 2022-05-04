package io.kenxue.cicd.application.application.appinfo.command.query;

import io.kenxue.cicd.application.application.appinfo.assembler.ApplicationInfo2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.application.applicationinfo.ApplicationInfoDTO;
import io.kenxue.cicd.coreclient.dto.application.applicationinfo.ApplicationInfoPageQry;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.domain.repository.application.ApplicationInfoRepository;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.domain.domain.application.ApplicationInfo;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Component
public class ApplicationInfoPageQryExe {

    @Resource
    private ApplicationInfoRepository applicationInfoRepository;
    @Resource
    private ApplicationInfo2DTOAssembler applicationInfo2DTOAssembler;

    public PageResponse<ApplicationInfoDTO> execute(ApplicationInfoPageQry qry) {
        Page<ApplicationInfo> page = applicationInfoRepository.page(qry);
        return PageResponse.of(applicationInfo2DTOAssembler.toDTOPage(page));
    }
}
