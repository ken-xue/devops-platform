package io.kenxue.cicd.domain.factory.kubernetes;

import io.kenxue.cicd.domain.domain.kubernetes.ClusterNode;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
public class ClusterNodeFactory {
    public static ClusterNode getClusterNode(){
        return new ClusterNode();
    }
}
