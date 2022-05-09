package io.kenxue.cicd.coreclient.dto.machine;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Data
@Accessors(chain = true)
public class MachineInfoListQry extends CommonCommand {
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
