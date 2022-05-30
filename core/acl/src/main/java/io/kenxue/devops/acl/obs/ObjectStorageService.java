package io.kenxue.devops.acl.obs;

/**
 * 统一对象存储接口
 */
public interface ObjectStorageService {
    void setString(String bucket,String key,String data);
    String getString(String bucket,String key);
}
