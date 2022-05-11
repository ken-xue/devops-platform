package io.kenxue.cicd.coreclient.dto.machine;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Data
@Accessors(chain = true)
public class MachineInfoDTO extends CommonDTO {
    /**
     * 服务器ip
     */
    @NotBlank
    private String ip;
    /**
     * 连接端口
     */
    @NotNull
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
    @NotBlank
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
     * 所属分组（标签）
     */
    private List<String> groupList;
    /**
     * 分组uuid（查询列表）
     */
    private String groupUuid;
}
