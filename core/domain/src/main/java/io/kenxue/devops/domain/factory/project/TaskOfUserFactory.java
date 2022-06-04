package io.kenxue.devops.domain.factory.project;

import io.kenxue.devops.domain.domain.project.TaskOfUser;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
public class TaskOfUserFactory {
    public static TaskOfUser getTaskOfUser(){
        return new TaskOfUser();
    }
}
