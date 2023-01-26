package system

import (
	"github.com/gin-gonic/gin"
	"server/adapter/api/system"
	"server/adapter/middleware"
)

type UserRouter struct{}

func (s *UserRouter) InitUserRouter(Router *gin.RouterGroup) {
	router := Router.Group("sys/user").Use(middleware.GinContextToContextMiddleware())
	adapter := system.AdapterUser
	{
		router.GET("self", adapter.Self) //获取当前用户信息
		router.GET("info", adapter.Info) //获取用户信息
		router.GET("page", adapter.Page)
		router.PUT("update", adapter.Update)
		router.POST("add", adapter.Add)
		router.DELETE("delete", adapter.Delete)
	}
}
