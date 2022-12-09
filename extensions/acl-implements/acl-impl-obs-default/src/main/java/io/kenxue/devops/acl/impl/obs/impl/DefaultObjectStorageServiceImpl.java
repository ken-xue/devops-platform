package io.kenxue.devops.acl.impl.obs.impl;

import io.kenxue.devops.acl.impl.obs.builder.ObsBuilder;
import io.kenxue.devops.acl.obs.ObjectStorageService;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Objects;

import static io.kenxue.devops.coreclient.exception.code.ObsErrorCode.SYSTEM_OBS_NOT_CONFIG;

/**
 * 统一对象存储接口
 * see: https://docs.min.io/docs/java-client-api-reference
 */
@Slf4j
@Service
public class DefaultObjectStorageServiceImpl implements ObjectStorageService {

    private MinioClient minioClient;

    @Override
    public void initialize(String config) {
        log.info("old client : {}",minioClient);
        this.minioClient = ObsBuilder.buildMinioClient(config);
        log.info("new client : {}",minioClient);
    }

    @SneakyThrows
    @Override
    public void set(String bucket, String key, InputStream inputStream) {
        if (Objects.isNull(minioClient))throw new RuntimeException(SYSTEM_OBS_NOT_CONFIG.getDesc());
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
        if (Objects.isNull(minioClient))throw new RuntimeException(SYSTEM_OBS_NOT_CONFIG.getDesc());
        InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(key)
                .build());
        return inputStream;
    }

    @SneakyThrows
    @Override
    public void setString(String bucket, String key, String context) {
        if (Objects.isNull(minioClient))throw new RuntimeException(SYSTEM_OBS_NOT_CONFIG.getDesc());
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
        if (Objects.isNull(minioClient))throw new RuntimeException(SYSTEM_OBS_NOT_CONFIG.getDesc());
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
}
