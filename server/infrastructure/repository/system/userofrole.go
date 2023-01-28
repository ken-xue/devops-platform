package system

import (
	"server/client/common/request"
	infra "server/infrastructure"
	"server/infrastructure/model/system"
)

type UserOfRoleRepository struct {
}

var UserOfRoleRepo = new(UserOfRoleRepository)

func (service *UserOfRoleRepository) DeleteUserOfRole(cmd request.DeleteCmd) error {
	return infra.DB.Where("id in ?", cmd.Ids).Delete(&system.UserOfRole{}).Error
}

func (service *UserOfRoleRepository) AddUserOfRole(userOfRole system.UserOfRole) error {
	return infra.DB.Create(&userOfRole).Error
}

func (service *UserOfRoleRepository) HasUserByRoleUuid(uuid string) (cnt int64, err error) {
	err = infra.DB.Model(&system.UserOfRole{}).Where("role_uuid = ?", uuid).Count(&cnt).Error
	return
}

// GetUserOfRoleByUserUuid 通过用户UUID获取其角色列表
func (service *UserOfRoleRepository) GetUserOfRoleByUserUuid(uuid string) (userOfRoles []system.UserOfRole, err error) {
	err = infra.DB.Model(&system.UserOfRole{}).Where("user_uuid = ?", uuid).Find(&userOfRoles).Error
	return
}
