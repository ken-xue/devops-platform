package io.kenxue.cicd.domain.factory.project;

import io.kenxue.cicd.domain.domain.project.ProjectOfUser;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
public class ProjectOfUserFactory {
    public static ProjectOfUser getProjectOfUser(){
        return new ProjectOfUser();
    }
}
