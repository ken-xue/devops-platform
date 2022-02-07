package io.kenxue.cicd.adapter.rest.sys;

import io.kenxue.cicd.adapter.common.annotation.Log;
import io.kenxue.cicd.adapter.common.annotation.Permissions;
import io.kenxue.cicd.adapter.rest.common.BasicController;
import io.kenxue.cicd.coreclient.api.sys.UserAppService;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.user.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "用户模块",description = "包含用户注册/列表/删除")
@RequestMapping("sys/user")
public class UserController extends BasicController {
    @Resource
    private UserAppService userAppService;
    @Resource
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * 注册用户 默认开启白名单
     * @param userAddCmd
     */
    @Log("添加用户")
    @ApiOperation(value = "添加",httpMethod = "POST")
    @PostMapping("/add")
    public Response add(@RequestBody @Valid UserAddCmd userAddCmd) {
        userAddCmd.getUserDTO().setUserPassword(bCryptPasswordEncoder.encode(userAddCmd.getUserDTO().getUserPassword()));
        return userAppService.add(userAddCmd);
    }
    /**
     * 删除
     * @param userDeleteCmd
     * @return
     */
    @Log("删除用户")
    @DeleteMapping("/delete")
    @Permissions("sys:user:delete")
    @ApiOperation(value = "删除",httpMethod = "DELETE")
    public Response delete(@RequestBody @Valid UserDeleteCmd userDeleteCmd){
        return userAppService.delete(userDeleteCmd);
    }
    /**
     * 列表
     */
    @GetMapping("/page")
    @Permissions("sys:user:page")
    @ApiOperation(value = "列表",httpMethod = "GET")
    public Response page(@ModelAttribute UserPageQry userPageQry){
        return userAppService.page(userPageQry);
    }
    /**
     * 获取当前用户详情包括：权限，角色
     */
    @GetMapping("/info")
    @Permissions("sys:user:info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response info(@ModelAttribute UserGetQry userGetQry){
        return userAppService.getById(userGetQry);
    }
    /**
     *详情
     */
    @GetMapping("/self/info")
    @ApiOperation(value = "详情",httpMethod = "GET")
    public Response self(){
        String userId = UserThreadContext.get();
        return userAppService.getInfoByUserId(new UserFullGetQry().setUserId(userId));
    }
    /**
     * 修改
     */
    @Log("修改用户")
    @PutMapping("/update")
    @Permissions("sys:user:update")
    @ApiOperation(value = "更新",httpMethod = "PUT")
    public Response update(@RequestBody UserUpdateCmd userUpdateCmd){
        userUpdateCmd.setBCryptPassword(bCryptPasswordEncoder.encode(userUpdateCmd.getUserCO().getUserPassword()));
        return userAppService.update(userUpdateCmd);
    }
}
