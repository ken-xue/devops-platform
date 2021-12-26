package io.kenxue.cicd.domain.factory.application;

import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
public class ApplicationPipelineFactory {
    public static ApplicationPipeline getApplicationPipeline(){
        return new ApplicationPipeline();
    }
}
