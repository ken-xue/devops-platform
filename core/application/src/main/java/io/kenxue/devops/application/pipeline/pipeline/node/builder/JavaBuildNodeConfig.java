package io.kenxue.devops.application.pipeline.pipeline.node.builder;

import lombok.Data;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@Data
public class JavaBuildNodeConfig {
    /**
     * 逗号分割符号
     */
    public static final String SPLIT_PATH = ";";
    /**
     * 构建的执行脚本
     */
    private String buildScript;
    /**
     * jdk版本
     */
    private String jdk;
    /**
     * maven版本
     */
    private String mvn;
    /**
     * 打包路径逗号分割
     */
    private String packagePath;
    /**
     * 通知方式
     */
    private String notice;

    public List<String> getPackagePaths(){
        Assert.hasLength(packagePath,"未配置打包路径");
        String[] paths = packagePath.split(";");
        return Arrays.asList(paths);
    }
}
