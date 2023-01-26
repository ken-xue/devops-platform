package system

import (
	"github.com/gin-gonic/gin"
	"github.com/go-playground/validator/v10"
	"server/adapter/common"
	"server/application/system"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
)

type RoleAdapter struct {
	common.CommonAdapter
}

var AdapterRole = new(RoleAdapter)

// Page
// @Tags     角色模块
// @Summary  分页
// @Success  200   {object}  response.Response
// @Router   /sys/role/page [get]
func (p *RoleAdapter) Page(c *gin.Context) {
	//绑定参数
	var cmd request.PageQuery
	err := c.ShouldBindQuery(&cmd)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	pageResult, err := roleServiceApp.Page(cmd)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(pageResult, c)
}

func (p *RoleAdapter) Info(c *gin.Context) {
	qry := cmd.RoleInfoQry{}
	c.ShouldBindQuery(&qry)
	data, err := roleServiceApp.Info(qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(data, c)
}

// Add User
// @Tags     角色表模块
// @Summary  新增角色表
// @Produce   application/json
// @Param    data  body      cmd.RoleAddCmd     true  "用户信息"
// @Success  200   {object}  response.Response
// @Router   /sys/role/add [post]
// Insert 增
func (p *RoleAdapter) Add(c *gin.Context) {
	//绑定参数
	var cmd cmd.RoleAddCmd
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
	cmd.Ops = p.Ops(c)
	err = system.RoleApp.Add(cmd)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Delete Role
// @Tags     角色表模块
// @Summary  删除角色表
// @Produce   application/json
// @Param    data  body      request.DeleteCmd     true  "删除角色表"
// @Success  200   {object}  response.Response
// @Router   /sys/role/delete [delete]
func (p *RoleAdapter) Delete(c *gin.Context) {
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
	err = system.RoleApp.Delete(ids)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Update Role
// @Tags     角色表模块
// @Summary  修改角色表
// @Produce   application/json
// @Param    data  body      cmd.UserUpdateCmd     true  "修改角色表"
// @Success  200   {object}  response.Response
// @Router   /sys/role/update [put]
func (p *RoleAdapter) Update(c *gin.Context) {
	//绑定参数
	var command cmd.RoleUpdateCmd
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
	err = system.RoleApp.Update(command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// List Role
// @Tags     角色表模块
// @Summary  角色列表
// @Produce   application/json
// @Param    data  body      cmd.RoleListQry     true  "角色列表"
// @Success  200   {object}  response.Response
// @Router   /sys/role/list [get]
func (p *RoleAdapter) List(c *gin.Context) {
	var query cmd.RoleListQry
	err := c.ShouldBindQuery(&query)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	ret, err := roleServiceApp.List(query)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(ret, c)
}
