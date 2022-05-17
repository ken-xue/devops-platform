package io.kenxue.cicd.infrastructure.repositoryimpl.middleware.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Data
@TableName("middleware_zookeeper")
public class ZookeeperDO extends CommonEntity {
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
