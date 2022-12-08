package io.kenxue.devops.minio;

import io.kenxue.devops.acl.obs.ObjectStorageService;
import io.kenxue.devops.sharedataboject.common.obs.BucketEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;
import java.util.UUID;

@SpringBootTest
public class MinioTest {

    @Resource
    private ObjectStorageService objectStorageService;

    @Test
    void upload() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append(UUID.randomUUID());
        }
        objectStorageService.setString(BucketEnum.PIPELINE_NODE_LOGGER.getName(),
                UUID.randomUUID().toString().replaceAll("-",""),
                sb.toString()
                );
    }

}
