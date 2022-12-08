package io.kenxue.devops.acl.impl.obs.config;

import io.kenxue.devops.sharedataboject.common.obs.BucketEnum;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectStorageServiceConfig {

    @Value("${minio.url}")
    private String url;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient getMiniClient() throws Exception {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
        //首次运行创建必要bucket
        for (BucketEnum bucket : BucketEnum.values()) {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket.getName()).build())) {
                MakeBucketArgs build = MakeBucketArgs.builder().bucket(bucket.getName()).build();
                minioClient.makeBucket(build);
            }
        }
        return minioClient;
    }
}
