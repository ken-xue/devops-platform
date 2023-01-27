package initialize

import (
	bus "github.com/ken-xue/event-bus"
	"server/application/system/event"
	"server/application/system/handler"
)

func RegisterEventHandler() {
	bus.EventBus.RegisterWithName(event.RoleUpdateEvent{}.GetHandlerName(), handler.RoleUpdateEventHandler{})
	bus.EventBus.RegisterWithName(event.RoleAddEvent{}.GetHandlerName(), handler.RoleAddEventHandler{})
	bus.EventBus.RegisterWithName(event.RoleDeleteEvent{}.GetHandlerName(), handler.RoleDeleteEventHandler{})
}
