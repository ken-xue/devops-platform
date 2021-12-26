package io.kenxue.cicd.domain.factory.application;

import io.kenxue.cicd.domain.domain.application.ApplicationInfo;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
public class ApplicationInfoFactory {
    public static ApplicationInfo getApplicationInfo(){
        return new ApplicationInfo();
    }
}
