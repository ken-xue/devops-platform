package io.kenxue.devops.infrastructure.repositoryimpl.middleware.jenkins.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Data
@TableName("middleware_jenkins")
public class JenkinsDO extends CommonEntity {
    /**
     * 别名
     */
    private String name;
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
    /**
     * 备注
     */
    private String remark;
}
