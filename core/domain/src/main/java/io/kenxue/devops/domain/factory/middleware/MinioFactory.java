package io.kenxue.devops.domain.factory.middleware;

import io.kenxue.devops.domain.domain.middleware.Minio;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
public class MinioFactory {
    public static Minio getMinio(){
        return new Minio();
    }
}
