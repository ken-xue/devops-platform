package io.kenxue.cicd.acl.obs;

/**
 * 统一对象存储接口
 */
public interface ObjectStorageService<T> {
    T get(String key);
    void save(T t);
}
