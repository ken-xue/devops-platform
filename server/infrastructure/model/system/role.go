package system

import (
	uuid "github.com/satori/go.uuid"
	"server/infrastructure/model"
)

type Role struct {
	model.CommonModel
	Name   string `json:"name" gorm:"comment:角色名称"`
	Remark string `json:"remark" gorm:"comment:备注"`
}

func (Role) TableName() string {
	return "sys_role"
}

type RoleOfMenu struct {
	model.CommonModel
	MenuUUID uuid.UUID `json:"menuUUID" gorm:"comment:MenuUUID"`
	RoleUUID uuid.UUID `json:"roleUUID" gorm:"comment:RoleUUID"`
}

func (RoleOfMenu) TableName() string {
	return "sys_role_of_menu"
}
