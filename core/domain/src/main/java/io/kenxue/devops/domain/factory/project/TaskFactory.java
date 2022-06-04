package io.kenxue.devops.domain.factory.project;

import io.kenxue.devops.domain.domain.project.Task;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
public class TaskFactory {
    public static Task getTask(){
        return new Task();
    }
}
