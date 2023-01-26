package router

import (
	"server/adapter/router/dev"
	"server/adapter/router/system"
)

type RouterGroup struct {
	System system.RouterGroup
	Dev    dev.RouterGroup
}

var RouterGroupApp = new(RouterGroup)
