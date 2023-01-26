package system

import (
	"github.com/gin-gonic/gin"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
)

type SysRoleAdapter struct{}

// Page
// @Tags     角色模块
// @Summary  分页
// @Success  200   {object}  response.Response
// @Router   /sys/role/page [get]
func (p *SysRoleAdapter) Page(c *gin.Context) {
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

func (p *SysRoleAdapter) Info(c *gin.Context) {
	qry := cmd.RoleInfoQry{}
	c.ShouldBindQuery(&qry)
	data, err := roleServiceApp.Info(qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(data, c)
}
func (p *SysRoleAdapter) Add(c *gin.Context) {

}
func (p *SysRoleAdapter) Update(c *gin.Context) {

}
func (p *SysRoleAdapter) Delete(c *gin.Context) {

}
func (p *SysRoleAdapter) List(c *gin.Context) {
	var query cmd.RoleListQry
	err := c.ShouldBindQuery(&query)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	ret := roleServiceApp.List(query)
	response.OkWithData(ret, c)
}
