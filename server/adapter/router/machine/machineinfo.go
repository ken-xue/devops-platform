package machine

import (
	"github.com/gin-gonic/gin"
	"server/adapter/api/machine"
	"server/adapter/middleware"
)

type MachineInfoRouter struct{}

func (s *MachineInfoRouter) InitUserRouter(Router *gin.RouterGroup) {
	router := Router.Group("machine/machineinfo").Use(middleware.GinContextToContextMiddleware())
	adapter := machine.AdapterMachineInfo
	{
		router.GET("info", adapter.Info)
		router.GET("page", adapter.Page)
		router.PUT("update", adapter.Update)
		router.POST("add", adapter.Add)
		router.DELETE("delete", adapter.Delete)
	}
}
