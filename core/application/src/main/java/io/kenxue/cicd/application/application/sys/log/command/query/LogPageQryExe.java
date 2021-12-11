package io.kenxue.cicd.application.application.sys.log.command.query;

import io.kenxue.cicd.application.application.sys.log.assembler.Log2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.sys.log.LogDTO;
import io.kenxue.cicd.coreclient.dto.sys.log.LogPageQry;
import io.kenxue.cicd.domain.domain.sys.Log;
import io.kenxue.cicd.domain.repository.sys.LogRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@Component
public class LogPageQryExe {

    @Resource
    private LogRepository logRepository;
    @Resource
    private Log2DTOAssembler log2DTOAssembler;

    public PageResponse<LogDTO> execute(LogPageQry qry) {
        Page<Log> page = logRepository.page(qry);
        return PageResponse.of(log2DTOAssembler.toDTOPage(page));
    }

}

