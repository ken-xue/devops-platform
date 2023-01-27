package initialize

import (
	"server/adapter"
	"server/config"
	"server/infrastructure"
	"server/logger"
)

func Init() {
	//加载配置
	config.InitConfig()
	//加载日志库
	logger.InitLogger()
	//加载路由
	adapter.InitAdapter()
	//加载ORM
	infra.InitInfra()
	//初始化socket服务器
	InitSocket()
	//注册事件处理器
	RegisterEventHandler()
}
