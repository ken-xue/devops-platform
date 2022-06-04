package io.kenxue.devops.domain.factory.project;

import io.kenxue.devops.domain.domain.project.TaskGroup;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
public class TaskGroupFactory {
    public static TaskGroup getTaskGroup(){
        return new TaskGroup();
    }
}
