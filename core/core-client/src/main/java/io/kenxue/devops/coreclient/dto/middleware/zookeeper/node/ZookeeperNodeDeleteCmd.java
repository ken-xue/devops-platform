package io.kenxue.devops.coreclient.dto.middleware.zookeeper.node;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Data
@Accessors(chain = true)
public class ZookeeperNodeDeleteCmd extends CommonCommand {
    @NotNull
    private String name;
    private String uuid;
}
