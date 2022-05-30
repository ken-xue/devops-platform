package io.kenxue.devops.repositoryimpl;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ZookeeperOps {
    static String prefix = "/kenxue";
    public CuratorFramework get(){
        CuratorFramework framework = CuratorFrameworkFactory.builder()
                .connectString("1.117.208.193:2181")
                .retryPolicy(new BoundedExponentialBackoffRetry(5000,5000,5000))
                .sessionTimeoutMs(60000)
                .connectionTimeoutMs(15000)
                .build();
        framework.start();
        return framework;
    }

    @Test
    public void monitor() throws Exception {
        CuratorFramework framework = get();
        Stat stat = framework.setData().forPath("/h", "hhhhh".getBytes(StandardCharsets.UTF_8));
        //String s = framework.create().forPath("/h", "hhhhh".getBytes(StandardCharsets.UTF_8));
        System.out.println(stat);
    }

    @Test
    public void createRoot() throws Exception {
        CuratorFramework framework = get();
        framework.create()
                .creatingParentContainersIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/kenxue","devops-platform".getBytes(StandardCharsets.UTF_8));
        List<String> kenxue = framework.getChildren().forPath("/kenxue");
        System.out.println(kenxue);
    }

    @Test
    public void CreateI() throws Exception {
        CuratorFramework framework = get();
        framework.create().creatingParentContainersIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/kenxue/lock/lock1/lock2/lock3/lock4/lock-3","dis-lock".getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void list() throws Exception {
        CuratorFramework framework = get();
        List<String> strings = framework.getChildren().forPath("/kenxue/lock/lock1");
        strings.forEach(node -> {
            System.out.println(node);
        });
    }

    @Test
    public void getData() throws Exception {
        CuratorFramework framework = get();
        byte[] bytes = framework.getData().forPath("/kenxue");
        String s = new String(bytes);
        System.out.println("data   "+s);
        CuratorFrameworkState state = framework.getState();
        System.out.println("state  "+state);
        QuorumVerifier currentConfig = framework.getCurrentConfig();
        System.out.println("config  "+currentConfig);
    }
}
