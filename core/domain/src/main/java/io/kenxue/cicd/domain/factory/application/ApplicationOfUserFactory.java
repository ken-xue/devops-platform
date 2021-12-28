package io.kenxue.cicd.domain.factory.application;

import io.kenxue.cicd.domain.domain.application.ApplicationOfUser;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
public class ApplicationOfUserFactory {
    public static ApplicationOfUser getApplicationOfUser(){
        return new ApplicationOfUser();
    }
}
