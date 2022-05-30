package io.kenxue.devops.adapter.rest.sys;

import io.kenxue.devops.adapter.common.annotation.Log;
import io.kenxue.devops.adapter.common.annotation.Permissions;
import io.kenxue.devops.adapter.rest.common.BasicController;
import io.kenxue.devops.coreclient.api.sys.MenuAppService;
import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.menu.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
/**
 * 菜单表
 * @author mikey
 * @date 2021-11-17 14:43:50
 */
@RestController
@Api(tags = "菜单表模块",description = "包含新增/列表/删除")
@RequestMapping("sys/menu")
public class MenuController extends BasicController {
    @Resource
    private MenuAppService menuAppService;

    @Log("添加菜单")
    @PostMapping("/add")
    @ApiOperation(value = "添加",httpMethod = "POST")
    @Permissions("sys:menu:add")
    public Response add(@RequestBody @Valid MenuAddCmd menuAddCmd) {
        return menuAppService.add(menuAddCmd);
    }

    @Log("删除菜单")
    @DeleteMapping("/delete")
    @Permissions("sys:menu:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid MenuDeleteCmd menuDeleteCmd){
        return menuAppService.delete(menuDeleteCmd);
    }

    @GetMapping("/page")
    @Permissions("sys:menu:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute @Valid MenuPageQry menuPageQry){
        return menuAppService.page(menuPageQry);
    }

    @GetMapping("/list")
    @Permissions("sys:menu:list")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response list(@ModelAttribute @Valid MenuListQry menuListQry){
        return menuAppService.list(menuListQry);
    }

    /**
     * 用于提供前端渲染路由
     * @return
     */
    @GetMapping("/auth")
    @ApiOperation(value = "菜单权限",httpMethod = "GET")
    public Response auth(){
        String userId = UserThreadContext.get();
        return menuAppService.getAuthMenu(new AuthMenuGetQry().setUserId(userId));
    }

    @GetMapping("/select")
    @Permissions("sys:menu:select")
    @ApiOperation(value = "下拉框",httpMethod = "GET")
    public Response select(MenuGetSelectQry menuGetSelectQry){
        return menuAppService.select(menuGetSelectQry);
    }

    @GetMapping("/info")
    @Permissions("sys:menu:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute @Valid MenuGetQry menuGetQry){
        return menuAppService.getById(menuGetQry);
    }

    @Log("修改菜单")
    @PutMapping("/update")
    @Permissions("sys:menu:update")
    @ApiOperation(value = "更新",httpMethod = "POST")
    public Response update(@RequestBody MenuUpdateCmd menuUpdateCmd){
        return menuAppService.update(menuUpdateCmd);
    }
}
