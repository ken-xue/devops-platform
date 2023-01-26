package handler

import (
	"fmt"
	bus "github.com/ken-xue/event-bus"
	ev "server/application/system/event"
	"server/logger"
)

type RoleAddEventHandler struct {
}

func (p RoleAddEventHandler) Execute(event bus.Event) (re bus.Response) {
	e := event.(ev.RoleAddEvent)
	logger.Log.Info(fmt.Sprintf("event : %#v", e))

	return
}
