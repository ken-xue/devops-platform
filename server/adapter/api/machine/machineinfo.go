package machine

import (
	"github.com/gin-gonic/gin"
	"github.com/go-playground/validator/v10"
	"server/adapter/common"
	"server/application/machine"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/machine/cmd"
)

type MachineInfoAdapter struct {
    common.CommonAdapter
}

var AdapterMachineInfo = new(MachineInfoAdapter)

// Info User
// @Tags     服务器节点模块
// @Summary  详情服务器节点
// @Produce   application/json
// @Param    data  body      cmd.MachineInfoAddCmd     true  "服务器节点信息"
// @Success  200   {object}  response.Response
// @Router   /machine/machineinfo/info [get]
// Info 详情
func (p *MachineInfoAdapter) Info(c *gin.Context) {
	qry := cmd.MachineInfoInfoQry{}
	err := c.ShouldBindQuery(&qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	data, err := machine.MachineInfoApp.Info(qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(data, c)
}

// Add User
// @Tags     服务器节点模块
// @Summary  新增服务器节点
// @Produce   application/json
// @Param    data  body      cmd.MachineInfoAddCmd     true  "用户信息"
// @Success  200   {object}  response.Response
// @Router   /machine/machineinfo/add [post]
// Insert 增
func (p *MachineInfoAdapter) Add(c *gin.Context) {
	//绑定参数
	var cmd cmd.MachineInfoAddCmd
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
	err = machine.MachineInfoApp.Add(cmd)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Delete User
// @Tags     服务器节点模块
// @Summary  删除服务器节点
// @Produce   application/json
// @Param    data  body      request.DeleteCmd     true  "删除服务器节点"
// @Success  200   {object}  response.Response
// @Router   /machine/machineinfo/delete [delete]
func (p *MachineInfoAdapter) Delete(c *gin.Context) {
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
	err = machine.MachineInfoApp.Delete(ids)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Update User
// @Tags     服务器节点模块
// @Summary  修改服务器节点
// @Produce   application/json
// @Param    data  body      cmd.UserUpdateCmd     true  "修改服务器节点"
// @Success  200   {object}  response.Response
// @Router   /machine/machineinfo/update [put]
func (p *MachineInfoAdapter) Update(c *gin.Context) {
	//绑定参数
	var command cmd.MachineInfoUpdateCmd
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
	err = machine.MachineInfoApp.Update(command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Page User
// @Tags     服务器节点模块
// @Summary  查询服务器节点
// @Produce   application/json
// @Param    data  query      request.PageQuery    true  "分页参数"
// @Success  200   {object}  response.Response
// @Router   /machine/machineinfo/page [get]
func (p *MachineInfoAdapter) Page(c *gin.Context) {
	//绑定参数
	var page request.PageQuery
	err := c.ShouldBindQuery(&page)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	pageResult, err := machine.MachineInfoApp.Page(page)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(pageResult, c)
}
