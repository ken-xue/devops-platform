package event

import (
	"reflect"
	"server/client/system/cmd"
	"server/infrastructure/model/system"
)

type RoleAddEvent struct {
	MenuList []string
	RoleUuid string
}

func (r RoleAddEvent) GetHandlerName() string {
	return reflect.TypeOf(r).Name()
}

type RoleDeleteEvent struct {
	Ids []uint
}

func (r RoleDeleteEvent) GetHandlerName() string {
	return reflect.TypeOf(r).Name()
}

type RoleUpdateEvent struct {
	Ids  []uint
	Role system.Role
	Cmd  cmd.RoleUpdateCmd
}

func (r RoleUpdateEvent) GetHandlerName() string {
	return reflect.TypeOf(r).Name()
}
