package io.kenxue.devops.domain.factory.kubernetes;

import io.kenxue.devops.domain.domain.kubernetes.ClusterNode;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
public class ClusterNodeFactory {
    public static ClusterNode getClusterNode(){
        return new ClusterNode();
    }
}
