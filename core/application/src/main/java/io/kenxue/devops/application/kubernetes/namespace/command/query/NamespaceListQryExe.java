package io.kenxue.devops.application.kubernetes.namespace.command.query;

import io.kenxue.devops.application.kubernetes.manager.ClusterCacheManager;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.kubernetes.namespace.NamespaceListQry;
import io.kenxue.devops.domain.domain.kubernetes.Namespace;
import io.kenxue.devops.domain.repository.kubernetes.NamespaceRepository;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/15:55
 * @Description:
 */
@Component
public class NamespaceListQryExe {

    @Resource
    private ClusterCacheManager cacheManager;

    public MultiResponse<Namespace> list(NamespaceListQry namespaceListQry) {
        ApiClient apiClient = cacheManager.get(namespaceListQry.getId());
        Configuration.setDefaultApiClient(apiClient);
        CoreV1Api api = new CoreV1Api();
        List<Namespace> namespaces = new ArrayList<>();
        try {
            V1NamespaceList list = api.listNamespace(null, null, null, null, null, null, null, null, null);
            list.getItems().forEach(name -> {
                Namespace namespace = new Namespace();
                namespace.setNamespaceName(name.getMetadata().getName());
                namespace.setCreationTime(name.getMetadata().getCreationTimestamp().toDate());
                namespaces.add(namespace);
            });
        }catch (ApiException e){
            e.printStackTrace();
        }
        return MultiResponse.of(namespaces);
    }
}
