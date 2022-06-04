package io.kenxue.devops.domain.factory.project;

import io.kenxue.devops.domain.domain.project.TaskOperatorLogger;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
public class TaskOperatorLoggerFactory {
    public static TaskOperatorLogger getTaskOperatorLogger(){
        return new TaskOperatorLogger();
    }
}
