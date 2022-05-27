package io.kenxue.cicd.acl.impl.obs;

import io.kenxue.cicd.acl.obs.ObjectStorageService;
import io.kenxue.cicd.sharedataboject.common.obs.BucketEnum;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 统一对象存储接口
 * see: https://docs.min.io/docs/java-client-api-reference
 */
//@Slf4j
public class DefaultObjectStorageServiceImpl implements ObjectStorageService<File> {

    static MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://47.104.202.181:9001")//注意这里使用的是API端口而不是视图端口
                    .credentials("", "")
                    .build();

    @Override
    public File get(String key) {
        return null;
    }

    @Override
    public void save(File file) {

    }

    public List<?> getList() {

        return null;
    }

    public static void main(String[] args) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.listBuckets().forEach(bucket -> {
            String name = bucket.name();
            System.out.println(name);
        });

        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BucketEnum.PIPELINE_LOGGER.getName()).build())) {
            MakeBucketArgs build = MakeBucketArgs.builder().bucket(BucketEnum.PIPELINE_LOGGER.getName()).build();
            minioClient.makeBucket(build);
        }


        FileInputStream inputStream = new FileInputStream("/Users/biaoyang/IdeaProjects/cicd-platform/Dockerfile");
        String key = BucketEnum.PIPELINE_LOGGER.getName() + "-" + System.currentTimeMillis() + ".log";
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(BucketEnum.PIPELINE_LOGGER.getName())
                        .object(key)
                        .stream(inputStream, inputStream.available(), 5 * 1024 * 1024)
                        .build());

        GetObjectResponse response = minioClient.getObject(GetObjectArgs.builder()
                .bucket(BucketEnum.PIPELINE_LOGGER.getName())
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
        System.out.println(sb);



        StatObjectResponse resp = minioClient.statObject(StatObjectArgs.builder()
                .bucket(BucketEnum.PIPELINE_LOGGER.getName())
                .object(key)
                .build());
        System.out.println(resp);


    }

}
