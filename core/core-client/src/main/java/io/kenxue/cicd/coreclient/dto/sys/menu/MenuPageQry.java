package io.kenxue.cicd.coreclient.dto.sys.menu;

import io.kenxue.cicd.coreclient.dto.common.command.PageQuery;
import lombok.Data;

/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@Data
public class MenuPageQry extends PageQuery {

    private MenuDTO menuCO;

}
