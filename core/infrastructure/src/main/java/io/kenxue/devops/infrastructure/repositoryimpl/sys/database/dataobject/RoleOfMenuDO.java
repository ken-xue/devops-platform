package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Data
@TableName("sys_role_of_menu")
public class RoleOfMenuDO extends CommonEntity {
    /**
     * 角色UUID
     */
    @TableId
    private String roleUuid;
    /**
     * 菜单UUID
     */
    private String menuUuid;
}
