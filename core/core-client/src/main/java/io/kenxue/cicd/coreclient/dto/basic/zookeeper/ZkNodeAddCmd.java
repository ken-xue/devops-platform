package io.kenxue.cicd.coreclient.dto.basic.zookeeper;

import lombok.Data;

/**
 * @author 刘牌
 * @Title:
 * @date 2022/2/2216:33
 */
@Data
public class ZkNodeAddCmd {
    private String name;
    private String data;
    private Integer type;
}
