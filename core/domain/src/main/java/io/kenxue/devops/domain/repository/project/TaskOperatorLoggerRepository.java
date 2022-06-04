package io.kenxue.devops.domain.repository.project;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerListQry;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerPageQry;
import io.kenxue.devops.domain.domain.project.TaskOperatorLogger;
import java.util.List;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
public interface TaskOperatorLoggerRepository {
    void create(TaskOperatorLogger taskOperatorLogger);
    void update(TaskOperatorLogger taskOperatorLogger);
    TaskOperatorLogger getById(Long id);
    List<TaskOperatorLogger> list(TaskOperatorLoggerListQry taskOperatorLoggerListQry);
    Page<TaskOperatorLogger> page(TaskOperatorLoggerPageQry qry);
}
