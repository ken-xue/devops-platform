package io.kenxue.devops.domain.factory.project;

import io.kenxue.devops.domain.domain.project.TaskFile;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
public class TaskFileFactory {
    public static TaskFile getTaskFile(){
        return new TaskFile();
    }
}
