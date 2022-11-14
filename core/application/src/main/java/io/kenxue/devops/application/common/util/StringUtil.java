package io.kenxue.devops.application.common.util;

public class StringUtil {
    /**
     * 获取git链接中项目名字
     * @param repository
     * @return
     */
    public static String getRepositoryName(String repository){
       return repository.substring(repository.lastIndexOf("/")+1,repository.lastIndexOf("."));
    }
}
