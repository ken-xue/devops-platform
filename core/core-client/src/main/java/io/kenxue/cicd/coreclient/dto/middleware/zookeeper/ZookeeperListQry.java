package io.kenxue.cicd.coreclient.dto.middleware.zookeeper;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Data
@Accessors(chain = true)
public class ZookeeperListQry extends CommonCommand {
    /**
     * 名称
     */
    private String name;
    /**
     * 连接地址串
     */
    private String address;
    /**
     * 会话超时时间
     */
    private Integer sessionTimeout;
    /**
     * 规格
     */
    private String specs;
    /**
     * 备注
     */
    private String remark;
}
