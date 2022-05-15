package io.kenxue.cicd.domain.domain.middleware;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jenkins extends CommonEntity {
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
