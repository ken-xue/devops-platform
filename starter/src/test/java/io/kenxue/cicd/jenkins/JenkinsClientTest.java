package io.kenxue.cicd.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.offbytwo.jenkins.model.QueueReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.Date;
import java.util.Map;

@SpringBootTest
@Slf4j
public class JenkinsClientTest {

    public static final String jobXml = "<!-- 1. ci -->\n" +
            "<project>\n" +
            "    <actions></actions>\n" +
            "    <description>Build and test the app.</description>\n" +
            "    <keepDependencies>false</keepDependencies>\n" +
            "    <properties>\n" +
            "        <com.coravy.hudson.plugins.github.GithubProjectProperty>\n" +
            "            <projectUrl>https://github.com/ken-xue/cicd-platform.git</projectUrl>\n" +
            "        </com.coravy.hudson.plugins.github.GithubProjectProperty>\n" +
            "    </properties>\n" +
            "    <canRoam>true</canRoam>\n" +
            "    <disabled>false</disabled>\n" +
            "    <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>\n" +
            "    <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>\n" +
            "    <triggers></triggers>\n" +
            "    <concurrentBuild>false</concurrentBuild>\n" +
            "    <builders>\n" +
            "        <hudson.plugins.gradle.Gradle>\n" +
            "            <switches></switches>\n" +
            "            <tasks>test</tasks>\n" +
            "            <rootBuildScriptDir></rootBuildScriptDir>\n" +
            "            <buildFile></buildFile>\n" +
            "            <gradleName>(Default)</gradleName>\n" +
            "            <useWrapper>true</useWrapper>\n" +
            "            <makeExecutable>false</makeExecutable>\n" +
            "            <fromRootBuildScriptDir>true</fromRootBuildScriptDir>\n" +
            "            <useWorkspaceAsHome>false</useWorkspaceAsHome>\n" +
            "            <passAllAsSystemProperties>false</passAllAsSystemProperties>\n" +
            "            <passAllAsProjectProperties>false</passAllAsProjectProperties>\n" +
            "        </hudson.plugins.gradle.Gradle>\n" +
            "    </builders>\n" +
            "    <publishers>\n" +
            "        <hudson.tasks.junit.JUnitResultArchiver>\n" +
            "            <testResults>build/test-results/**/*.xml</testResults>\n" +
            "            <keepLongStdio>false</keepLongStdio>\n" +
            "            <testDataPublishers></testDataPublishers>\n" +
            "            <allowEmptyResults>false</allowEmptyResults>\n" +
            "            <healthScaleFactor>1.0</healthScaleFactor>\n" +
            "        </hudson.tasks.junit.JUnitResultArchiver>\n" +
            "    </publishers>\n" +
            "    <buildWrappers></buildWrappers>\n" +
            "    <scm class='hudson.plugins.git.GitSCM'>\n" +
            "        <userRemoteConfigs>\n" +
            "            <hudson.plugins.git.UserRemoteConfig>\n" +
            "                <url>https://github.com/ken-xue/cicd-platform.git</url>\n" +
            "            </hudson.plugins.git.UserRemoteConfig>\n" +
            "        </userRemoteConfigs>\n" +
            "        <branches>\n" +
            "            <hudson.plugins.git.BranchSpec>\n" +
            "                <name>**</name>\n" +
            "            </hudson.plugins.git.BranchSpec>\n" +
            "        </branches>\n" +
            "        <configVersion>2</configVersion>\n" +
            "        <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>\n" +
            "        <gitTool>Default</gitTool>\n" +
            "        <browser class='hudson.plugins.git.browser.GithubWeb'>\n" +
            "            <url>https://github.com/ken-xue/cicd-platform.git</url>\n" +
            "        </browser>\n" +
            "    </scm>\n" +
            "</project>";

    // 高版本Jenkins api 调用出现403
    // Manage Jenkins —>Script Console 添加 hudson.security.csrf.GlobalCrumbIssuerConfiguration.DISABLE_CSRF_PROTECTION=true
    // dsl see
    // http://job-dsl.herokuapp.com/
    // https://jenkinsci.github.io/job-dsl-plugin/#path/job
//    @Test
    public void test() {
        try {
            JenkinsServer jenkins = new JenkinsServer(new URI("http://101.200.54.149:8080"), "admin", "admin-kenxue");
            Map<String, Job> jobs = jenkins.getJobs();
            JobWithDetails job = jobs.get("test-job-1").details();
            log.info("job:{}",job);
            log.info("job xml:{}",jenkins.getJobXml("test-job-1"));
        String jobName = "java-client-create-job-" + DateFormatUtils.format(new Date(), "yyyy-MM-dd-HH-mm-ss");
        jenkins.createJob(jobName,jobXml);
        QueueReference build = jenkins.getJob(jobName).build();
        String queueItemUrlPart = build.getQueueItemUrlPart();
        log.info(queueItemUrlPart);
    } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
