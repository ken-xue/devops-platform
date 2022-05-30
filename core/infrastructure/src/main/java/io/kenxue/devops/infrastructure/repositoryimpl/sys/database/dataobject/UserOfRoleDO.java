package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Data
@TableName("sys_user_of_role")
public class UserOfRoleDO extends CommonEntity {
    /**
     * 用户UUID
     */
    @TableId
    private String userUuid;
    /**
     * 角色UUID
     */
    private String roleUuid;
}
