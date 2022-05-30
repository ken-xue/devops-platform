package io.kenxue.devops.coreclient.dto.middleware.zookeeper;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Data
@Accessors(chain = true)
public class ZookeeperAddCmd extends CommonCommand {
    @NotNull
    private ZookeeperDTO zookeeperDTO;
}
