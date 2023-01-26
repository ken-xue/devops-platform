package {{.Module}}

import (
	"github.com/gin-gonic/gin"
	"server/adapter/api/{{.Module}}"
	"server/adapter/middleware"
)

type {{.StructName}}Router struct{}

func (s *{{.StructName}}Router) InitUserRouter(Router *gin.RouterGroup) {
	router := Router.Group("{{.Module}}/{{.AllLowName}}").Use(middleware.GinContextToContextMiddleware())
	adapter := {{.Module}}.Adapter{{.StructName}}
	{
		router.GET("info", adapter.Info)
		router.GET("page", adapter.Page)
		router.PUT("update", adapter.Update)
		router.POST("add", adapter.Add)
		router.DELETE("delete", adapter.Delete)
	}
}
