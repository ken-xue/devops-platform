package io.kenxue.cicd.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.Map;

@SpringBootTest
@Slf4j
public class JenkinsClientTest {

    @Test
    private void test() throws Exception {
        JenkinsServer jenkins = new JenkinsServer(new URI("http://localhost:8080/jenkins"), "admin", "password");
        Map<String, Job> jobs = jenkins.getJobs();
        JobWithDetails job = jobs.get("My Job").details();
        log.info("job:{}",job);
    }
}
