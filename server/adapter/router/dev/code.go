package dev

import (
	"github.com/gin-gonic/gin"
	"server/adapter/api"
	"server/adapter/middleware"
)

type CodeRouter struct{}

func (s *CodeRouter) InitCodeRouter(Router *gin.RouterGroup) {
	router := Router.Group("dev/code").Use(middleware.GinContextToContextMiddleware())
	api := api.AdapterGroup.DevAdapterGroup.CodeAdapter
	{
		router.GET("page", api.Page)
		router.POST("create", api.Create)
		router.POST("download", api.Download)
		router.DELETE("delete", api.Delete)
	}
}
