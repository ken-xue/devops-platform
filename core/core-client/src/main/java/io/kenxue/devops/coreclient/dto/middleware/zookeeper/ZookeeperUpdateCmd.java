package io.kenxue.devops.coreclient.dto.middleware.zookeeper;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Data
@Accessors(chain = true)
public class ZookeeperUpdateCmd extends CommonCommand {

    @NotNull
    private ZookeeperDTO zookeeperDTO;
}
