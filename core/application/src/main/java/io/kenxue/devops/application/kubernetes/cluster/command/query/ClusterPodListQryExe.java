package io.kenxue.devops.application.kubernetes.cluster.command.query;

import io.kenxue.devops.application.kubernetes.manager.ClusterCacheManager;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterPodDTO;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterPodListQry;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1PodList;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class ClusterPodListQryExe {
    @Resource
    private ClusterCacheManager cacheManager;

    public MultiResponse<ClusterPodDTO> execute(ClusterPodListQry qry) {
        ApiClient apiClient = cacheManager.get(qry.getId());
        Configuration.setDefaultApiClient(apiClient);
        CoreV1Api api = new CoreV1Api();
        List<ClusterPodDTO> pods = new ArrayList<>();
        try {
            V1PodList list;
            if (StringUtils.isNotBlank(qry.getNamespace())){
                 list = api.listNamespacedPod(qry.getNamespace(), null, null, null, null, null, null, null, null,null);
            } else {
                list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
            }
            list.getItems().forEach(item -> {
                ClusterPodDTO pod = ClusterPodDTO.builder()
                        //meta
                        .name(item.getMetadata().getName())
                        .createTime(item.getMetadata().getCreationTimestamp().toDate())
                        //spec
                        .nodeName(item.getSpec().getNodeName())
                        //status
                        .hostIP(item.getStatus().getHostIP())
                        .podIP(item.getStatus().getPodIP())
                        .podIPs(item.getStatus().getPodIPs().stream().map(v->v.getIp()).collect(Collectors.toList()))
                        .phase(item.getStatus().getPhase())
                        .build();
                pods.add(pod);
            });
        }catch (ApiException e){
            e.printStackTrace();
        }
        return MultiResponse.of(pods);
    }
}
