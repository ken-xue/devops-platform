package io.kenxue.cicd.coreclient.api.sys;


import io.kenxue.cicd.coreclient.dto.sys.menu.*;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;


/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
public interface MenuAppService {
    Response add(MenuAddCmd cmd);
    Response update(MenuUpdateCmd cmd);
    Response delete(MenuDeleteCmd userDeleteCmd);
    SingleResponse<MenuDTO> getById(MenuGetQry qry);
    MultiResponse<MenuDTO> list(MenuListQry qry);
    PageResponse<MenuDTO> page(MenuPageQry userPageQry);
    //下拉框选择
    Response select(MenuGetSelectQry menuGetSelectQry);
    //获取授权菜单
    Response getAuthMenu(AuthMenuGetQry authMenuGetQry);
}
