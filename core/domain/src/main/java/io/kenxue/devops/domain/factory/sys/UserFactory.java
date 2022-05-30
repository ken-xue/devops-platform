package io.kenxue.devops.domain.factory.sys;

import io.kenxue.devops.domain.domain.sys.User;

public class UserFactory {

    public static User getUser(){
        //init the domain object
        return new User();
    }

}
