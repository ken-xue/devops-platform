package io.kenxue.devops.application.kubernetes.cluster.command.query;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterResourceQry;
import io.kenxue.devops.sharedataboject.kubernetes.enums.ResourceEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterResourceQryExe {
    @Resource
    private ClusterPodListQryExe clusterPodListQryExe;

    public MultiResponse execute(ClusterResourceQry qry) {

        String resource = qry.getResource();

        if (ResourceEnum.Pod.name().equals(resource)){
            return clusterPodListQryExe.execute(qry);
        }

        return MultiResponse.success();
    }
}
