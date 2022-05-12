package io.kenxue.cicd.domain.domain.machine;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineInfo extends CommonEntity {
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

    /**
     * 所属分组
     */
    private List<String> groupList;
}
