package io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes;

import io.kenxue.cicd.coreclient.dto.kubernetes.namespace.NamespaceListQry;
import io.kenxue.cicd.domain.domain.kubernetes.Namespace;
import io.kenxue.cicd.domain.repository.kubernetes.NamespaceRepository;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 刘牌
 * @Date: 2021/12/12/16:00
 * @Description:
 */
@Component
public class NamespaceRepositoryImpl implements NamespaceRepository {

    /**
     * 先进行测试，后面再抽取到kubernetes授权模块
     */
    static CoreV1Api api;
    static {
        String kubeConfigPath = "E:\\learn\\dev-cloud-admin\\admin-core\\infrastructure\\src\\main\\resources\\kube\\config";
        ApiClient client =
                null;
        try {
            client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration.setDefaultApiClient(client);
        api = new CoreV1Api();
    }

    @Override
    public List<Namespace> list(NamespaceListQry namespaceListQry) {
        List<Namespace> namespaces = new ArrayList<>();
        try {
            V1NamespaceList list = api.listNamespace(null, null, null, null, null, null, null, null, null);
            list.getItems().forEach(name -> {
                Namespace namespace = new Namespace();
                namespace.setNamespaceName(name.getMetadata().getName());
                namespace.setCreationTimestamp(Objects.requireNonNull(name.getMetadata().getCreationTimestamp()).toString());
                namespaces.add(namespace);
            });
        }catch (ApiException e){
            e.printStackTrace();
        }
        return namespaces;
    }

    @Override
    public void add(String namespaceName) {
        V1Namespace v1Namespace = new V1Namespace();
        v1Namespace.setApiVersion("v1");
        v1Namespace.setKind("Namespace");
        V1ObjectMeta v1ObjectMeta = new V1ObjectMeta();
        v1ObjectMeta.setName("kenxue-pre-prod");
        v1Namespace.setMetadata(v1ObjectMeta);
        try {
            V1Namespace namespace = api.createNamespace(v1Namespace, null, null, null);
        }catch (ApiException apiException){
            apiException.printStackTrace();
        }
    }
}