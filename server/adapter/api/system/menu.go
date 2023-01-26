package system

import (
	"github.com/gin-gonic/gin"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
	"server/util"
)

type SysMenuAdapter struct{}

// Auth
// @Tags     菜单模块
// @Summary  验证码
// @Success  200   {object}  response.Response
// @Router   /sys/menu/auth [get]
func (p *SysMenuAdapter) Auth(c *gin.Context) {
	//1.请求体获取token
	uuid := util.GetUserUuid(c)
	//判断缓存中是否存在，如果存在则直接返回，否则去数据库查
	menus := menuServiceApp.GetByUserUUID(uuid)
	//3.查询对应的菜单权限
	response.OkWithData(menus, c)
}

// Page
// @Tags     菜单模块
// @Summary  分页
// @Success  200   {object}  response.Response
// @Router   /sys/menu/page [get]
func (p *SysMenuAdapter) Page(c *gin.Context) {
	//绑定参数
	var page request.PageQuery
	err := c.ShouldBindQuery(&page)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	pageResult, err := menuServiceApp.Page(page)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(pageResult, c)
}

func (p *SysMenuAdapter) Info(c *gin.Context) {

}
func (p *SysMenuAdapter) Add(c *gin.Context) {

}
func (p *SysMenuAdapter) Update(c *gin.Context) {

}
func (p *SysMenuAdapter) Delete(c *gin.Context) {

}

// List
// @Tags     菜单模块
// @Summary  分页
// @Param    data  body      cmd.MenuListQry	false	"菜单列表"
// @Success  200   {object}  response.Response
// @Router   /sys/menu/list [get]
func (p *SysMenuAdapter) List(c *gin.Context) {
	var qry cmd.MenuListQry
	err := c.ShouldBindQuery(&qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	list, err := menuServiceApp.List(qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(list, c)
}

// Select
// @Tags     菜单模块
// @Summary  下拉框数据
// @Param    data  body      cmd.MenuListQry	false	"菜单列表"
// @Success  200   {object}  response.Response
// @Router   /sys/menu/query [get]
func (p *SysMenuAdapter) Select(c *gin.Context) {
	var qry cmd.MenuSelectQry
	err := c.ShouldBindQuery(&qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	list, err := menuServiceApp.Select(qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(list, c)
}
