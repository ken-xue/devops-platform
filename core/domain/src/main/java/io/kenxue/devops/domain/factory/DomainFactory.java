package io.kenxue.devops.domain.factory;

import io.kenxue.devops.domain.domain.sys.User;
import io.kenxue.devops.domain.domain.sys.UserOfRole;

public class DomainFactory {

    public static User getUser(){
        return new User();
    }

    public static UserOfRole getUserOfRole(){
        return new UserOfRole();
    }
}
