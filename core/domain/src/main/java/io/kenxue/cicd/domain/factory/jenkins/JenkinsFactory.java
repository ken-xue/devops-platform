package io.kenxue.cicd.domain.factory.jenkins;

import io.kenxue.cicd.domain.domain.jenkins.Jenkins;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
public class JenkinsFactory {
    public static Jenkins getJenkins(){
        return new Jenkins();
    }
}
