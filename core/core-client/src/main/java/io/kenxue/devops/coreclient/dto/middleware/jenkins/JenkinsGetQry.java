package io.kenxue.devops.coreclient.dto.middleware.jenkins;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Data
@Accessors(chain = true)
public class JenkinsGetQry extends CommonCommand {

    private Long id;
    /**
     * jenkins uri
     */
    private String uri;
    /**
     * 访问用户名
     */
    private String username;
    /**
     * 访问密码或秘钥
     */
    private String password;
}