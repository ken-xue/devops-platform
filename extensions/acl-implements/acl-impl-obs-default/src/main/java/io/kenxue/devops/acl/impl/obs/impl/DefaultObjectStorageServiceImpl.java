package io.kenxue.devops.acl.impl.obs.impl;

import io.kenxue.devops.acl.obs.ObjectStorageService;
import io.kenxue.devops.sharedataboject.common.obs.BucketEnum;
import io.minio.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 统一对象存储接口
 * see: https://docs.min.io/docs/java-client-api-reference
 */
@Slf4j
@Service
public class DefaultObjectStorageServiceImpl implements ObjectStorageService, InitializingBean {

    private final MinioClient minioClient;

    public DefaultObjectStorageServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @SneakyThrows
    @Override
    public void set(String bucket, String key, InputStream inputStream) {
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(key)
                        .stream(inputStream,inputStream.available(),-1)
                        .build());
    }

    @SneakyThrows
    @Override
    public InputStream get(String bucket, String key) {
        InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(key)
                .build());
        return inputStream;
    }

    @SneakyThrows
    @Override
    public void setString(String bucket, String key, String context) {
        InputStream inputStream = new ByteArrayInputStream(context.getBytes());
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(key)
                        .stream(inputStream,context.getBytes().length,-1)
                        .build());
    }

    @SneakyThrows
    @Override
    public String getString(String bucket, String key) {
        GetObjectResponse response = minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(key)
                .build());
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[1024];
        while (response.available() >= 0) {
            int i = response.read(buffer, 0, 1024);
            if (i < 0) {
                break;
            }
            String resp = new String(buffer, 0, i);
            sb.append(resp);
        }
        return sb.toString();
    }

    /**
     * 首次运行创建必要bucket
     * @throws Exception e
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        for (BucketEnum bucket : BucketEnum.values()) {
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket.getName()).build())) {
                MakeBucketArgs build = MakeBucketArgs.builder().bucket(bucket.getName()).build();
                minioClient.makeBucket(build);
            }
        }
    }
}
