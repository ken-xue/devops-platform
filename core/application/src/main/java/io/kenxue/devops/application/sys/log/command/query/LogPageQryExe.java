package io.kenxue.devops.application.sys.log.command.query;

import io.kenxue.devops.application.sys.log.assembler.Log2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.sys.log.LogDTO;
import io.kenxue.devops.coreclient.dto.sys.log.LogPageQry;
import io.kenxue.devops.domain.domain.sys.Log;
import io.kenxue.devops.domain.repository.sys.LogRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

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

