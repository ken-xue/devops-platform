package io.kenxue.cicd.infrastructure.repositoryimpl.middleware.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Data
@TableName("middleware_minio")
public class MinioDO extends CommonEntity {
    /**
     * uri
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
     * 别名
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 访问端口
     */
    private Integer port;
}
