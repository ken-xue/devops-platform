package io.kenxue.devops.coreclient.dto.middleware.zookeeper;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Data
@Accessors(chain = true)
public class ZookeeperPageQry extends PageQuery {

    private ZookeeperDTO zookeeperDTO;

}
