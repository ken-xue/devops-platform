package io.kenxue.devops.coreclient.dto.machine;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Data
@Accessors(chain = true)
public class MachineInfoGetQry extends CommonCommand {

    private Long id;
    /**
     * 服务器ip
     */
    private String ip;
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
    /**
     * 是否是master节点
     */
    private Integer masterNode;
}