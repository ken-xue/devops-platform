package event

import (
	"reflect"
)

type RoleAddEvent struct {
	MenuList []string
}

func (r RoleAddEvent) GetHandlerName() string {
	return reflect.TypeOf(RoleAddEvent{}).Name()
}
