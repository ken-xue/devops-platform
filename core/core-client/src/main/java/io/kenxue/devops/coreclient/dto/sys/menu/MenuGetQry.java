package io.kenxue.devops.coreclient.dto.sys.menu;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Data
public class MenuGetQry extends CommonCommand {

    private Long id;
    /**
     * 父菜单uuid
     */
    private String menuParentUuid;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 菜单url
     */
    private String menuUrl;
    /**
     * 授权标识
     */
    private String menuPerms;
    /**
     * 0:目录 1:菜单 2:按钮
     */
    private String menuType;
    /**
     * 图标
     */
    private String menuIcon;
    /**
     * 排序
     */
    private Integer menuOrder;
    /**
     * 备注
     */
    private String menuRemark;
}