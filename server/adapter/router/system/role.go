package system

import (
	"github.com/gin-gonic/gin"
	"server/adapter/api/system"
	"server/adapter/middleware"
)

type RoleRouter struct{}

func (s *MenuRouter) InitRoleRouter(Router *gin.RouterGroup) {
	router := Router.Group("/sys/role").Use(middleware.GinContextToContextMiddleware())
	adapter := system.AdapterRole
	{
		router.GET("/page", adapter.Page)        //分页
		router.GET("/info", adapter.Info)        //详情
		router.GET("/list", adapter.List)        //列表
		router.PUT("/update", adapter.Update)    //更新
		router.POST("/add", adapter.Add)         //添加
		router.DELETE("/delete", adapter.Delete) //删除
	}
}
