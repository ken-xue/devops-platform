package io.kenxue.cicd.domain.repository.sys;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.sys.log.LogListQry;
import io.kenxue.cicd.coreclient.dto.sys.log.LogPageQry;
import io.kenxue.cicd.domain.domain.sys.Log;

import java.util.List;

/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
public interface LogRepository {
    void create(Log log);
    void update(Log log);
    Log getById(Long id);
    List<Log> list(LogListQry logListQry);
    Page<Log> page(LogPageQry qry);
}
