package system

import "server/application"

type SysAdapterGroup struct {
	SysLoginAdapter
	SysMenuAdapter
	SysRoleAdapter
}

var (
	userServiceApp  = application.ServiceGroupApp.SystemServiceAppGroup.UserServiceApp
	loginServiceApp = application.ServiceGroupApp.SystemServiceAppGroup.LoginServiceApp
	menuServiceApp  = application.ServiceGroupApp.SystemServiceAppGroup.MenuServiceApp
	roleServiceApp  = application.ServiceGroupApp.SystemServiceAppGroup.RoleServiceApp
)
