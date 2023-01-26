package middleware

import (
	"context"
	"github.com/gin-gonic/gin"
	"server/application"
	"server/client/common/response"
	infra "server/infrastructure"
	"server/logger"
	"server/util"
	"strings"
)

var menuServiceApp = application.ServiceGroupApp.SystemServiceAppGroup.MenuServiceApp

const AuthPrefix = "AUTH_"

// AuthHandler 拦截器
func AuthHandler() gin.HandlerFunc {
	return func(c *gin.Context) {
		user, _ := util.GetClaims(c)
		//获取请求的PATH
		path := c.Request.URL.Path
		// 获取请求方法
		method := strings.ToLower(c.Request.Method)
		// 判断缓存中是否存在，如果存在则直接返回，否则去数据库查
		if exist, _ := infra.Redis.Exists(context.Background(), AuthPrefix+user.UUID).Result(); exist == 0 {
			LoadCache(user.UUID)
		}
		member := path + ":" + method
		success, _ := infra.Redis.SIsMember(context.Background(), AuthPrefix+user.UUID, member).Result()
		if !success {
			logger.Log.Info("鉴权失败 " + member)
			response.FailWithDetailed(gin.H{}, "权限不足", c)
			c.Abort()
			return
		}
		c.Set("username", user.Username)
		c.Next()
	}
}

func LoadCache(uuid string) {
	// 查询对应的菜单权限
	menus := menuServiceApp.GetByUserUUID(uuid)
	api := make([]interface{}, len(menus))
	for _, menu := range menus {
		if menu.Api == "" || len(menu.Api) == 0 {
			continue
		}
		path := menu.Api + ":" + menu.Method
		api = append(api, path)
	}
	infra.Redis.SAdd(context.Background(), AuthPrefix+uuid, api...)
}
