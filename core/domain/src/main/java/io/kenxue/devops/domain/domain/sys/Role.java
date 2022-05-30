package io.kenxue.devops.domain.domain.sys;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色表
 * @author mikey
 * @date 2021-11-17 14:27:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends CommonEntity {
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 备注
     */
    private String roleRemark;
}
