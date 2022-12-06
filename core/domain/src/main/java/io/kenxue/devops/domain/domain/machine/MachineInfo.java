package io.kenxue.devops.domain.domain.machine;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Properties;

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

    /**
     * 测试连接
     */
    public void testConnection() throws JSchException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(accessUsername, ip, port);
        session.setPassword(accessPassword); // 设置密码
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(4000); // 设置timeout时间
        session.connect(); // 通过Session建立链接
        session.disconnect();
    }
}
