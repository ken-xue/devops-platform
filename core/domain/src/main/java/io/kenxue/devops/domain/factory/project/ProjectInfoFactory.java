package io.kenxue.devops.domain.factory.project;

import io.kenxue.devops.domain.domain.project.ProjectInfo;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
public class ProjectInfoFactory {
    public static ProjectInfo getProjectInfo(){
        return new ProjectInfo();
    }
}
