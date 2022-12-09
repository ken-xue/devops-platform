package io.kenxue.devops.acl.impl.obs.builder;

import com.alibaba.fastjson.JSONObject;
import io.kenxue.devops.coreclient.dto.sys.config.ConfigDTO;
import io.kenxue.devops.coreclient.exception.OBSException;
import io.kenxue.devops.sharedataboject.common.obs.BucketEnum;
import io.kenxue.devops.sharedataboject.system.ObsConfigDTO;
import io.kenxue.devops.sharedataboject.system.enums.ConfigEnum;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;

import static io.kenxue.devops.coreclient.exception.code.OBSErrorCode.SYSTEM_OBS_INIT_ERROR;

public class ObsBuilder {

    public static MinioClient buildMinioClient(String config){
        ConfigDTO configDTO = JSONObject.parseObject(config, ConfigDTO.class);
        ObsConfigDTO obsConfigDTO = JSONObject.parseObject(configDTO.getConfig(), ObsConfigDTO.class);
        MinioClient minioClient = MinioClient.builder()
                .endpoint(obsConfigDTO.getUrl())
                .credentials(obsConfigDTO.getUsername(), obsConfigDTO.getPassword())
                .build();
        //首次运行创建必要bucket
        if (ConfigEnum.DEFAULT_OBS.name().equals(configDTO.getName()))
        for (BucketEnum bucket : BucketEnum.values()) {
            try {
                if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket.getName()).build())) {
                    MakeBucketArgs build = MakeBucketArgs.builder().bucket(bucket.getName()).build();
                    minioClient.makeBucket(build);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new OBSException(SYSTEM_OBS_INIT_ERROR);
            }
        }
        return minioClient;
    }

}
