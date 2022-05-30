package io.kenxue.devops.domain.factory.kubernetes;

import io.kenxue.devops.domain.domain.kubernetes.Cluster;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
public class ClusterFactory {
    public static Cluster getCluster(){
        return new Cluster();
    }
}
