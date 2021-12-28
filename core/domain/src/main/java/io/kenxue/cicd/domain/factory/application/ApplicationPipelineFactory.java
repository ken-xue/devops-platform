package io.kenxue.cicd.domain.factory.application;

import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public class ApplicationPipelineFactory {
    public static ApplicationPipeline getApplicationPipeline(){
        return new ApplicationPipeline();
    }
}
