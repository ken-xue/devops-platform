package io.kenxue.cicd.infrastructure.repositoryimpl.jenkins.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
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
