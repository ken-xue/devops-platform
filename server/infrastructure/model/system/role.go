package system

import (
	"server/infrastructure/model"
)

type Role struct {
	model.CommonModel `mapstructure:",squash"`
	Name              string `json:"name" gorm:"comment:角色名称"`
	Remark            string `json:"remark" gorm:"comment:备注"`
	MenuList          []Menu `json:"menuList" gorm:"-" mapstructure:"squash"`
}

func (Role) TableName() string {
	return "sys_role"
}

type RoleOfMenu struct {
	model.CommonModel
	MenuUUID string `json:"menuUUID" gorm:"comment:MenuUUID"`
	RoleUUID string `json:"roleUUID" gorm:"comment:RoleUUID"`
}

func (RoleOfMenu) TableName() string {
	return "sys_role_of_menu"
}
