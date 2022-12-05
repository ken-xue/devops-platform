package io.kenxue.devops.domain.domain.kubernetes;

import io.kenxue.devops.domain.common.CommonEntity;
import io.kenxue.devops.sharedataboject.kubernetes.enums.AccessWayEnum;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cluster extends CommonEntity {
    /**
     * 集群名称
     */
    private String name;
    /**
     * 版本
     */
    private String version;
    /**
     * 秘钥
     */
    private String secretKey;

    private String accessUrl;
    /**
     * 集群配置文件
     */
    private String config;

    private byte[] configBytes;

    private String username;

    private String password;

    private String accessWay;

    public void testConnection(){
        try {
            ApiClient client;
            switch (AccessWayEnum.valueOf(accessWay)){
                case CONFIG_FILE:
                    client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(
                            new InputStreamReader(new ByteArrayInputStream(configBytes)))).build();
                    break;
                case TOKEN:
                    client = Config.fromToken(accessUrl,secretKey);
                    break;
                case UPWD:
                    client = Config.fromUserPassword(accessUrl,username,password);
                    break;
                default:
                    throw new RuntimeException("无法找到指定的访问方式");
            }
            Configuration.setDefaultApiClient(client);
            CoreV1Api api = new CoreV1Api();
            V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
            for (V1Pod item : list.getItems()) {
                System.out.println(item.getMetadata().getName());
            }
        }catch (Exception e){
            throw new RuntimeException("连接失败："+e.getMessage());
        }
    }
}
