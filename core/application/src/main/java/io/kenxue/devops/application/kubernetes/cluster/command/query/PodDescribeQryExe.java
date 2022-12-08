package io.kenxue.devops.application.kubernetes.cluster.command.query;

import com.alibaba.fastjson.JSONObject;
import io.kenxue.devops.application.kubernetes.manager.ClusterCacheManager;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterPodDescribeQry;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Component
public class PodDescribeQryExe {
    @Resource
    private ClusterCacheManager cacheManager;

    public SingleResponse<Object> execute(ClusterPodDescribeQry qry) {
        ApiClient apiClient = cacheManager.get(qry.getClusterId());
        Configuration.setDefaultApiClient(apiClient);
        CoreV1Api api = new CoreV1Api();
        V1Pod v1Pod = null;
        try {
            v1Pod = api.readNamespacedPod(qry.getName(), qry.getNamespace(), null, null, null);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return SingleResponse.of(JSONObject.toJSON(v1Pod));
    }
}
