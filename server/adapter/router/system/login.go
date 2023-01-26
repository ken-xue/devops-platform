package system

import (
	"github.com/gin-gonic/gin"
	"server/adapter/api"
	"server/adapter/middleware"
)

type LoginRouter struct{}

func (s *LoginRouter) InitLoginRouter(Router *gin.RouterGroup) {
	router := Router.Group("/").Use(middleware.GinContextToContextMiddleware())
	api := api.AdapterGroup.SysAdapterGroup.SysLoginAdapter
	{
		router.GET("captcha", api.Captcha) //获取验证码
		router.POST("login", api.Login)    //登录账号
		router.POST("logout", api.Logout)  //退出登录
	}
}
