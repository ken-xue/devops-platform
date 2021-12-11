package io.kenxue.cicd.domain.factory;

import io.kenxue.cicd.domain.domain.sys.User;
import io.kenxue.cicd.domain.domain.sys.UserOfRole;

public class DomainFactory {

    public static User getUser(){
        return new User();
    }

    public static UserOfRole getUserOfRole(){
        return new UserOfRole();
    }
}
