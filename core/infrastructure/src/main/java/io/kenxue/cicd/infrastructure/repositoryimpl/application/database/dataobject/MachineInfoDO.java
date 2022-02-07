package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Data
@TableName("machine_info")
public class MachineInfoDO extends CommonEntity {
    /**
     * 服务器ip
     */
    private String ip;
    /**
     * 连接端口
     */
    private Integer port;
    /**
     * 服务器名
     */
    private String name;
    /**
     * 访问方式
     */
    private String accessWay;
    /**
     * 服务器登陆用户名
     */
    private String accessUsername;
    /**
     * 服务器登陆密码
     */
    private String accessPassword;
    /**
     * 服务器登陆密钥
     */
    private String accessKey;
}
