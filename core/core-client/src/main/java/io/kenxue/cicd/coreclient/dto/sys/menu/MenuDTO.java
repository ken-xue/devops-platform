package io.kenxue.cicd.coreclient.dto.sys.menu;


import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Data
@Accessors(chain = true)
public class MenuDTO extends CommonDTO {
    /**
     * 父菜单uuid
     */
    private String menuParentUuid;
    /**
     * 父级菜单名称
     */
    private String menuParentName;
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
    private Integer menuType;
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
    /**
     * 是否有之菜单
     */
    private Boolean hasChildren;
}
