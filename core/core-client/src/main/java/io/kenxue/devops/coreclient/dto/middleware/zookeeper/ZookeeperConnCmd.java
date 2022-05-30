package io.kenxue.devops.coreclient.dto.middleware.zookeeper;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1414:40
 */
@Data
@Accessors(chain = true)
public class ZookeeperConnCmd {
    @NotNull
    private ZookeeperDTO zookeeperDTO;
}
