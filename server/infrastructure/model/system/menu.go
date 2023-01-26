package system

import (
	"server/infrastructure/model"
)

type Menu struct {
	model.CommonModel
	ParentUUID  string `json:"parentUUID" gorm:"comment:父菜单"`
	Name        string `json:"name" gorm:"comment:名称"`
	Url         string `json:"url" gorm:"comment:前端路由路径"`
	Api         string `json:"api" gorm:"comment:后端接口路径"`
	Method      string `json:"method" gorm:"comment:姓名"`
	Type        int    `json:"type" gorm:"comment:类型"`
	Icon        string `json:"icon" gorm:"comment:图标"`
	Order       int    `json:"order" gorm:"comment:排序"`
	Remark      int    `json:"remark" gorm:"comment:备注"`
	Hide        int    `json:"hide" gorm:"comment:是否隐藏"`
	HasChildren bool   `json:"hasChildren" gorm:"-"`
}

func (Menu) TableName() string {
	return "sys_menu"
}
