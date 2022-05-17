//package io.kenxue.cicd.repositoryimpl;
//
//
//import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZkTreeNode;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.framework.imps.CuratorFrameworkState;
//import org.apache.curator.retry.BoundedExponentialBackoffRetry;
//import org.apache.zookeeper.CreateMode;
//import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
//import org.junit.Test;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ZookeeperOps {
//    static String prefix = "/kenxue";
//    public CuratorFramework get(){
//        CuratorFramework framework = CuratorFrameworkFactory.builder()
//                .connectString("116.198.160.39:2182")
//                .retryPolicy(new BoundedExponentialBackoffRetry(5000,5000,5000))
//                .sessionTimeoutMs(60000)
//                .connectionTimeoutMs(15000)
//                .build();
//        framework.start();
//        return framework;
//    }
//
//    @Test
//    public void createRoot() throws Exception {
//        CuratorFramework framework = get();
//        framework.create()
//                .creatingParentContainersIfNeeded()
//                .withMode(CreateMode.PERSISTENT)
//                .forPath("/kenxue","cicd-platform".getBytes(StandardCharsets.UTF_8));
//        List<String> kenxue = framework.getChildren().forPath("/kenxue");
//        System.out.println(kenxue);
//    }
//
//    @Test
//    public void CreateI() throws Exception {
//        CuratorFramework framework = get();
//        framework.create().creatingParentContainersIfNeeded()
//                .withMode(CreateMode.PERSISTENT)
//                .forPath("/kenxue/lock/lock1/lock2/lock3/lock4/lock-3","dis-lock".getBytes(StandardCharsets.UTF_8));
//    }
//
//    @Test
//    public void list() throws Exception {
//        CuratorFramework framework = get();
//        List<String> strings = framework.getChildren().forPath("/kenxue/lock/lock1");
//        strings.forEach(node -> {
//            System.out.println(node);
//        });
//    }
//
//    @Test
//    public void getData() throws Exception {
//        CuratorFramework framework = get();
//        byte[] bytes = framework.getData().forPath("/kenxue");
//        String s = new String(bytes);
//        System.out.println("data   "+s);
//        CuratorFrameworkState state = framework.getState();
//        System.out.println("state  "+state);
//        QuorumVerifier currentConfig = framework.getCurrentConfig();
//        System.out.println("config  "+currentConfig);
//    }
//
//    @Test
//    public void delete() throws Exception {
//        CuratorFramework framework = get();
//        Void unused = framework.delete().forPath("/kenxue");
//    }
//
//    @Test
//    public void tree() throws Exception {
//        CuratorFramework framework = get();
//        List<String> list = framework.getChildren().forPath("/");
//        System.out.println(list);
//        List<ZkTreeNode> nodeList = new ArrayList<>();
//        list.forEach(node -> {
//            ZkTreeNode treeNode = new ZkTreeNode();
//            treeNode.setId(node);
//            treeNode.setLabel(node);
//            treeNode.setParentId("/");
//            try {
//                treeNode.setParentFlag(framework.getChildren().forPath("/"+node).size() > 0);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            nodeList.add(treeNode);
//        });
//        System.out.println(nodeList);
//    }
//}
