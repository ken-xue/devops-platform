package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Data
@TableName("sys_role")
public class RoleDO extends CommonEntity {
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 备注
     */
    private String roleRemark;
}
