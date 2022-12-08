package io.kenxue.devops.application.kubernetes.cluster.command;

import io.kenxue.devops.acl.obs.ObjectStorageService;
import io.kenxue.devops.application.kubernetes.cluster.assembler.Cluster2DTOAssembler;
import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterAddCmd;
import io.kenxue.devops.coreclient.dto.kubernetes.cluster.ClusterImportCmd;
import io.kenxue.devops.domain.domain.kubernetes.Cluster;
import io.kenxue.devops.domain.repository.kubernetes.ClusterRepository;
import io.kenxue.devops.sharedataboject.common.obs.BucketEnum;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Component
public class ClusterImportCmdExe {

    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private Cluster2DTOAssembler cluster2DTOAssembler;
    @Resource
    private ObjectStorageService obs;

    @SneakyThrows
    public Response execute(ClusterImportCmd cmd) {
        //1.根据访问方式进行访问测试
        Cluster cluster = cluster2DTOAssembler.toDomain(cmd.getInfo());
        cluster.testConnection();
        cluster.create(UserThreadContext.get());
        //保存配置文件到obs
        obs.set(BucketEnum.KUBERNETES_CONFIG_FILE.getName(),cluster.getUuid(),new ByteArrayInputStream(cluster.getConfigBytes()));
        cluster.setConfig(cluster.getUuid());
        //保存数据到数据库
        clusterRepository.create(cluster);
        return Response.success();
    }
}
