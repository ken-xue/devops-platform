package io.kenxue.devops.application.kubernetes.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.kenxue.devops.acl.cache.CacheService;
import io.kenxue.devops.acl.obs.ObjectStorageService;
import io.kenxue.devops.domain.domain.kubernetes.Cluster;
import io.kenxue.devops.domain.repository.kubernetes.ClusterRepository;
import io.kenxue.devops.sharedataboject.common.obs.BucketEnum;
import io.kenxue.devops.sharedataboject.util.FileUtil;
import io.kubernetes.client.openapi.ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 集群实例缓存
 */
@Slf4j
@Service
public class ClusterCacheManager implements CacheService<Long,ApiClient> {

    Cache<Long, ApiClient> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(10, TimeUnit.MINUTES)//十分钟不访问过期
            .maximumSize(10000)//最大值
            .build();;//id,实例

    @Resource
    private ClusterRepository clusterRepository;
    @Resource
    private ObjectStorageService obs;

    @Override
    public ApiClient get(Long key) {
        try {
            return cache.get(key, () -> {
                Cluster cluster = clusterRepository.getById(key);
                if (cluster.getConfigBytes()==null||cluster.getConfigBytes().length==0){//尝试去obs拉取配置文件
                    try {
                        InputStream inputStream = obs.get(BucketEnum.KUBERNETES_CONFIG_FILE.getName(), cluster.getConfig());
                        cluster.setConfigBytes(FileUtil.of(inputStream));
                    }catch (Exception e){
                        e.printStackTrace();
                        log.error("集群配置文件拉取异常:"+e.getMessage());
                        throw e;
                    }
                }
                return cluster.getClient();
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Not found cached value: " + key);
    }

    @Override
    public void set(Long s, ApiClient client) {
        cache.put(s,client);
    }
}
