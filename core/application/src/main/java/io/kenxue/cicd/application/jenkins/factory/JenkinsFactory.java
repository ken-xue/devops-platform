package io.kenxue.cicd.application.jenkins.factory;

import com.offbytwo.jenkins.JenkinsServer;
import io.kenxue.cicd.domain.domain.jenkins.Jenkins;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class JenkinsFactory {

    public JenkinsServer create(Jenkins jenkins){
        URI url = null;
        try {
            url = new URI(jenkins.getUri());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //JenkinsServer jenkins = new JenkinsServer(new URI("http://101.200.54.149:8080"), "admin", "admin-kenxue");
        String username = jenkins.getUsername();
        String passwordOrToken = jenkins.getPassword();
        JenkinsServer jenkinsServer = new JenkinsServer(url, username, passwordOrToken);
        return jenkinsServer;
    }

}
