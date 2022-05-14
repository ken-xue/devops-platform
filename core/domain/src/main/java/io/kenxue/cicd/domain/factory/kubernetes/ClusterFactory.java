package io.kenxue.cicd.domain.factory.kubernetes;

import io.kenxue.cicd.domain.domain.kubernetes.Cluster;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
public class ClusterFactory {
    public static Cluster getCluster(){
        return new Cluster();
    }
}
