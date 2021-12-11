package io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.dataobject;


import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;

/**
 * UserDO
 *
 * @date 2019-02-27 5:00 PM
 */
@Data
@TableName("sys_user")
public class UserDO extends CommonEntity {
    /**
     * 域账号
     */
    private String userId;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 头像
     */
    private String avatar;
}
