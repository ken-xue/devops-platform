package io.kenxue.cicd.domain.factory.application;

import io.kenxue.cicd.domain.domain.application.ApplicationOfUser;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
public class ApplicationOfUserFactory {
    public static ApplicationOfUser getApplicationOfUser(){
        return new ApplicationOfUser();
    }
}
