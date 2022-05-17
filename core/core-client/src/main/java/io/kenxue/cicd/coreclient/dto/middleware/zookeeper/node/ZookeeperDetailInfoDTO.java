package io.kenxue.cicd.coreclient.dto.middleware.zookeeper.node;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘牌
 * @Title:
 * @date 2022/2/2121:05
 */
@Data
@Accessors(chain = true)
public class ZookeeperDetailInfoDTO {
    private String data;
    private String state;
    private String config;
}
