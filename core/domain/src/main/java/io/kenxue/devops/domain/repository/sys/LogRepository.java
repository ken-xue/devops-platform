package io.kenxue.devops.domain.repository.sys;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.sys.log.LogListQry;
import io.kenxue.devops.coreclient.dto.sys.log.LogPageQry;
import io.kenxue.devops.domain.domain.sys.Log;

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
