package adapter

import (
	"github.com/gin-gonic/gin"
	swaggerFiles "github.com/swaggo/files"
	ginSwagger "github.com/swaggo/gin-swagger"
	"server/adapter/middleware"
	"server/adapter/router"
)

var (
	Router *gin.Engine
)

// LoadRouter 加载路由
func loadRouter() *gin.Engine {

	Router := gin.Default()

	Router.Use(middleware.Cors())

	Router.GET("/swagger/*any", ginSwagger.WrapHandler(swaggerFiles.Handler))

	PublicGroup := Router.Group("/")
	// 健康监测
	{
		PublicGroup.GET("/ok", func(c *gin.Context) {
			c.JSON(200, "ok")
		})
	}
	systemRouter := router.RouterGroupApp.System

	PrivateGroup := Router.Group("/")
	//登录和获取验证码接口不拦截，其余均拦截
	{
		systemRouter.InitLoginRouter(PublicGroup)
	}

	PrivateGroup.Use(middleware.JWTAuth()).Use(middleware.AuthHandler())
	{
		systemRouter.InitMenuRouter(PrivateGroup)
		systemRouter.InitUserRouter(PrivateGroup)
		systemRouter.InitRoleRouter(PrivateGroup)
		//...
	}
	devRouter := router.RouterGroupApp.Dev
	{
		devRouter.InitCodeRouter(PrivateGroup)
	}

	return Router
}

func InitAdapter() {
	Router = loadRouter()
}
