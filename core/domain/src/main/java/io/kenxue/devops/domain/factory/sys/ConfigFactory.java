package io.kenxue.devops.domain.factory.sys;

import io.kenxue.devops.domain.domain.sys.Config;
/**
 * 系统配置表
 * @author 麦奇
 * @date 2022-12-08 19:34:34
 */
public class ConfigFactory {
    public static Config getConfig(){
        return new Config();
    }
}
