package system

import (
	"server/infrastructure/model"
)

type User struct {
	model.CommonModel `mapstructure:",squash"`
	Username          string   `json:"username" gorm:"index;comment:用户登录名"`
	Name              string   `json:"name" gorm:"comment:姓名"`
	Password          string   `json:"password" gorm:"comment:密码"`
	Email             string   `json:"email" gorm:"comment:邮箱"`
	Status            int      `json:"status" gorm:"comment:状态"`
	Avatar            string   `json:"avatar" gorm:"comment:头像"`
	RoleIdList        []string `json:"roleIdList" gorm:"-"`
}

func (User) TableName() string {
	return "sys_user"
}

type UserOfRole struct {
	model.CommonModel
	UserUUID string `json:"uuid" gorm:"comment:UserUUID"`
	RoleUUID string `json:"uuid" gorm:"comment:RoleUUID"`
}

func (UserOfRole) TableName() string {
	return "sys_user_of_role"
}
