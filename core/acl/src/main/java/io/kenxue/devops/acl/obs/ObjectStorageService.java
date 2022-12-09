package io.kenxue.devops.acl.obs;

import java.io.InputStream;

/**
 * 统一对象存储接口
 */
public interface ObjectStorageService {
    //
    default void initialize(String config){
      throw new RuntimeException("Method not implemented");
    }
    void setString(String bucket,String key,String data);
    String getString(String bucket,String key);
    void set(String bucket, String key, InputStream inputStream);
    InputStream get(String bucket, String key);
}
