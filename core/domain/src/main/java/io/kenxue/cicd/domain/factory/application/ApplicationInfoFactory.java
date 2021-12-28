package io.kenxue.cicd.domain.factory.application;

import io.kenxue.cicd.domain.domain.application.ApplicationInfo;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public class ApplicationInfoFactory {
    public static ApplicationInfo getApplicationInfo(){
        return new ApplicationInfo();
    }
}
