package system

import (
	"github.com/gin-gonic/gin"
	"github.com/go-playground/validator/v10"
	"server/adapter/common"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
	"server/util"
)

type UserAdapter struct {
	common.CommonAdapter
}

var AdapterUser = new(UserAdapter)

// Info User
// @Tags     用户模块
// @Summary  用户信息
// @Produce   application/json
// @Param    data  body      cmd.UserAddCmd     true  "用户信息"
// @Success  200   {object}  response.Response
// @Router   /sys/user/info [get]
// Info 详情
func (p *UserAdapter) Info(c *gin.Context) {
	qry := cmd.UserInfoQry{}
	err := c.ShouldBindQuery(&qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	data, err := userServiceApp.Info(qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(data, c)
}

// Add User
// @Tags     用户模块
// @Summary  添加用户
// @Produce   application/json
// @Param    data  body      cmd.UserAddCmd     true  "用户信息"
// @Success  200   {object}  response.Response
// @Router   /base/user/add [post]
// Insert 增
func (p *UserAdapter) Add(c *gin.Context) {
	//绑定参数
	var cmd cmd.UserAddCmd
	err := c.ShouldBindJSON(&cmd)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	//验证参数
	validate := validator.New()
	err = validate.Struct(cmd)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	cmd.Ops = c.GetString("username")
	err = userServiceApp.Add(cmd)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Delete User
// @Tags     用户模块
// @Summary  删除用户
// @Produce   application/json
// @Param    data  body      request.DeleteCmd     true  "删除用户"
// @Success  200   {object}  response.Response
// @Router   /base/user/delete [delete]
func (p *UserAdapter) Delete(c *gin.Context) {
	//绑定参数
	var ids request.DeleteCmd
	err := c.ShouldBindJSON(&ids)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	//验证参数
	validate := validator.New()
	err = validate.Struct(ids)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	err = userServiceApp.Delete(ids)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Update User
// @Tags     用户模块
// @Summary  修改用户
// @Produce   application/json
// @Param    data  body      cmd.UserUpdateCmd     true  "修改用户"
// @Success  200   {object}  response.Response
// @Router   /base/user/update [put]
func (p *UserAdapter) Update(c *gin.Context) {
	//绑定参数
	var command cmd.UserUpdateCmd
	err := c.ShouldBindJSON(&command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	//验证参数
	validate := validator.New()
	err = validate.Struct(command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	command.Ops = p.Ops(c)
	command.Ops = c.GetString("username")
	err = userServiceApp.Update(command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Page User
// @Tags     用户模块
// @Summary  查询用户
// @Produce   application/json
// @Param    data  query      request.PageQuery    true  "分页参数"
// @Success  200   {object}  response.Response
// @Router   /base/user/page [get]
func (p *UserAdapter) Page(c *gin.Context) {
	//绑定参数
	var page request.PageQuery
	err := c.ShouldBindQuery(&page)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	pageResult, err := userServiceApp.Page(page)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(pageResult, c)
}

// Self User
// @Tags     用户模块
// @Summary  用户信息
// @Produce   application/json
// @Success  200   {object}  response.Response
// @Router   /sys/user/self [get]
// Self 用户获取当前用户自己的登录信息
func (p *UserAdapter) Self(c *gin.Context) {
	id := util.GetUserID(c)
	user, _ := userServiceApp.Info(cmd.UserInfoQry{
		Id: id,
	})
	response.OkWithData(user, c)
}
