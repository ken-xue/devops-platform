package io.kenxue.cicd.coreclient.dto.sys.user;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import io.kenxue.cicd.coreclient.dto.sys.menu.MenuDTO;
import io.kenxue.cicd.coreclient.dto.sys.role.RoleDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true)
public class UserDTO extends CommonDTO {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String userPassword;
    private String userName;
    //验证码
    private String code;
    private String email;
    private String avatar;
    private Integer status;
    private Set<RoleDTO> roles;
    private Set<MenuDTO> menus;
    //角色uuid
    private List<String> roleIdList;
}
