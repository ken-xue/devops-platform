package io.kenxue.cicd.coreclient.dto.sys.userofrole;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户关联角色表
 * @author mikey
 * @date 2021-11-19 15:22:41
 */
@Data
@Accessors(chain = true)
public class UserOfRoleDTO {
    /**
     * 用户UUID
     */
    private String userUuid;
    /**
     * 角色UUID
     */
    private String roleUuid;
}
