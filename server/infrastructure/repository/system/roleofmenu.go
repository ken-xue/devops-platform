package system

import (
	infra "server/infrastructure"
	"server/infrastructure/model/system"
)

type RoleOfMenuRepository struct {
}

var RoleOfMenuRepo = new(RoleOfMenuRepository)

func (service *RoleOfMenuRepository) AddRoleOfMenu(roleOfMenu system.RoleOfMenu) error {
	return infra.DB.Create(&roleOfMenu).Error
}

func (service *RoleOfMenuRepository) DeleteRoleOfMenu(uuid string) error {
	return infra.DB.Where("role_uuid in ?", uuid).Delete(&system.RoleOfMenu{}).Error
}

func (service *RoleOfMenuRepository) GetRoleOfMenuByRoleUuid(uuid string) (roleOfMenus []system.RoleOfMenu, err error) {
	err = infra.DB.Where("role_uuid = ?", uuid).Find(&roleOfMenus).Error
	return
}
