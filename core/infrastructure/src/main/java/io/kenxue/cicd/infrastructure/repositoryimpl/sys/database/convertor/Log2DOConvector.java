package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.cicd.domain.domain.sys.Log;
import io.kenxue.cicd.infrastructure.common.convector.Convector;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject.LogDO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统日志
 * @author mikey
 * @date 2021-12-03 17:28:52
 */
@Component
public class Log2DOConvector implements Convector<Log, LogDO> {
    public LogDO toDO(Log log) {
        return Log2DOMapStruct.INSTANCE.toDO(log);
    }
    public Log toDomain(LogDO logDO) {
        return Log2DOMapStruct.INSTANCE.toDomain(logDO);
    }
    public List<LogDO> toDOList(List<Log> logList) {
        return Log2DOMapStruct.INSTANCE.toDOList(logList);
    }
    public List<Log> toDomainList(List<LogDO> logDOList) {
        return Log2DOMapStruct.INSTANCE.toDomainList(logDOList);
    }
}
