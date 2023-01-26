package system

import (
	"github.com/gin-gonic/gin"
	"server/adapter/api"
	"server/adapter/middleware"
)

type MenuRouter struct{}

func (s *MenuRouter) InitMenuRouter(Router *gin.RouterGroup) {
	router := Router.Group("/sys/menu").Use(middleware.GinContextToContextMiddleware())
	api := api.AdapterGroup.SysAdapterGroup.SysMenuAdapter
	{
		router.GET("/auth", api.Auth)        //授权
		router.GET("/page", api.Page)        //分页
		router.GET("/info", api.Info)        //详情
		router.GET("/list", api.List)        //列表
		router.PUT("/update", api.Update)    //更新
		router.POST("/add", api.Add)         //添加
		router.DELETE("/delete", api.Delete) //删除
	}
}
