package io.kenxue.devops.application.sys.user.service;

import io.kenxue.devops.application.sys.user.command.UserAddCmdExe;
import io.kenxue.devops.application.sys.user.command.UserUpdateCmdExe;
import io.kenxue.devops.application.sys.user.command.query.UserGetQryExe;
import io.kenxue.devops.application.sys.user.command.query.UserInfoGetQryExe;
import io.kenxue.devops.application.sys.user.command.query.UserListQryExe;
import io.kenxue.devops.application.sys.user.command.query.UserPageQryExe;
import io.kenxue.devops.application.sys.user.command.UserDeleteCmdExe;
import io.kenxue.devops.coreclient.api.sys.UserAppService;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.sys.user.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserServicempl
 *
 * @date 2019-02-28 6:22 PM
 */
@Service
public class UserAppAppServiceImpl implements UserAppService {
    @Resource
    private UserAddCmdExe userAddCmdExe;
    @Resource
    private UserUpdateCmdExe userUpdateCmdExe;
    @Resource
    private UserGetQryExe userGetQryExe;
    @Resource
    private UserListQryExe userListQryExe;
    @Resource
    private UserPageQryExe userPageQryExe;
    @Resource
    private UserDeleteCmdExe userDeleteCmdExe;
    @Resource
    private UserInfoGetQryExe userInfoGetQryExe;

    
    public Response add(UserAddCmd userAddCmd) {
        return  userAddCmdExe.execute(userAddCmd);
    }

    
    public Response update(UserUpdateCmd cmd) {
        return userUpdateCmdExe.execute(cmd);
    }

    
    public SingleResponse<UserDTO> getById(UserGetQry qry) {
        return userGetQryExe.execute(qry);
    }

    
    public MultiResponse<UserDTO> list(UserListQry qry) {
        return userListQryExe.execute(qry);
    }
    
    public Response delete(UserDeleteCmd userDeleteCmd) {
        return userDeleteCmdExe.execute(userDeleteCmd);
    }

    
    public PageResponse<UserDTO> page(UserPageQry userPageQry) {
        return userPageQryExe.execute(userPageQry);
    }

    
    public Response getInfoByUserId(UserFullGetQry userFullGetQry) {
        return userInfoGetQryExe.execute(userFullGetQry);
    }

}
