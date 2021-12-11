package io.kenxue.cicd.domain.domain.sys;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色关联菜单表
 * @author mikey
 * @date 2021-11-19 14:47:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleOfMenu extends CommonEntity {
    /**
     * 角色UUID
     */
    private String roleUuid;
    /**
     * 菜单UUID
     */
    private String menuUuid;

    /**
     * 创建
     * @param operator
     * @param roleUuid
     * @param menuUuid
     */
    public void create(String operator,String roleUuid,String menuUuid) {
        super.create(operator);
        this.roleUuid = roleUuid;
        this.menuUuid=menuUuid;
    }
}
