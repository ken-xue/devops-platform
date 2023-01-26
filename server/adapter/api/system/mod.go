package system

import "server/application"

type SysAdapterGroup struct {
	SysLoginAdapter
	SysMenuAdapter
	RoleAdapter
}

var (
	userServiceApp  = application.ServiceGroupApp.SystemServiceAppGroup.UserServiceApp
	loginServiceApp = application.ServiceGroupApp.SystemServiceAppGroup.LoginServiceApp
	menuServiceApp  = application.ServiceGroupApp.SystemServiceAppGroup.MenuServiceApp
	roleServiceApp  = application.ServiceGroupApp.SystemServiceAppGroup.RoleServiceApp
)
