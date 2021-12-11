package io.kenxue.cicd.domain.factory.sys;

import io.kenxue.cicd.domain.domain.sys.User;

public class UserFactory {

    public static User getUser(){
        //init the domain object
        return new User();
    }

}
