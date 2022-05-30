package io.kenxue.devops.domain.factory.application;

import io.kenxue.devops.domain.domain.application.ApplicationConfig;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public class ApplicationConfigFactory {
    public static ApplicationConfig getApplicationConfig(){
        return new ApplicationConfig();
    }
}
