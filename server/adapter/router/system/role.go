package system

import (
	"github.com/gin-gonic/gin"
	"server/adapter/api"
	"server/adapter/middleware"
)

type RoleRouter struct{}

func (s *MenuRouter) InitRoleRouter(Router *gin.RouterGroup) {
	router := Router.Group("/sys/role").Use(middleware.GinContextToContextMiddleware())
	api := api.AdapterGroup.SysAdapterGroup.SysRoleAdapter
	{
		router.GET("/page", api.Page)        //分页
		router.GET("/info", api.Info)        //详情
		router.GET("/list", api.List)        //列表
		router.PUT("/update", api.Update)    //更新
		router.POST("/add", api.Add)         //添加
		router.DELETE("/delete", api.Delete) //删除
	}
}
