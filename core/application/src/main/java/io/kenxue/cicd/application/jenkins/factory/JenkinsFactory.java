package io.kenxue.cicd.application.jenkins.factory;

import com.offbytwo.jenkins.JenkinsServer;

import java.net.URI;

public class JenkinsFactory {

    public JenkinsServer create(URI url,String username,String passwordOrToken){
        JenkinsServer jenkins = new JenkinsServer(url, username, passwordOrToken);
//        JenkinsServer jenkins = new JenkinsServer(new URI("http://101.200.54.149:8080"), "admin", "admin-kenxue");
        return jenkins;
    }

}
