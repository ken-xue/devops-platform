package system

import (
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
	infra "server/infrastructure"
	"server/infrastructure/model/system"
)

type RoleRepository struct{}

var RoleRepo = new(RoleRepository)

// Insert 增
func (service *RoleRepository) Insert(role system.Role) error {
	//设置基本信息
	role.Create("")
	return infra.DB.Create(&role).Error
}

// Delete 删
func (service *RoleRepository) Delete(cmd request.DeleteCmd) error {
	return infra.DB.Where("id in ?", cmd.Ids).Delete(&system.Role{}).Error
}

// Update 改
func (service *RoleRepository) Update(role system.Role) (err error) {
	return infra.DB.Where("id = ?", role.ID).First(&system.Role{}).Updates(&role).Error
}

// Page 查
func (service *RoleRepository) Page(query request.PageQuery) (pageResult response.PageResult, err error) {
	limit := query.PageSize
	offset := query.PageSize * (query.PageIndex - 1)
	db := infra.DB.Model(&system.Role{})
	var list []system.Role
	err = db.Count(&pageResult.Total).Error
	if err != nil {
		return
	}
	err = db.Limit(limit).Offset(offset).Find(&list).Error
	pageResult.List = list
	return
}

func (service *RoleRepository) GetById() (role system.Role, err error) {
	return
}

// GetByUserUUID 通过用户UUID获取其角色列表
func (service *RoleRepository) GetByUserUUID(uuid string) (list []system.Role) {
	//查找对应的角色
	userOfRoles := service.GetRoleOfUserUUID(uuid)
	roleUUIDList := make([]string, 0)
	for _, role := range userOfRoles {
		roleUUIDList = append(roleUUIDList, role.RoleUUID)
	}
	//查找角色对应的权限
	infra.DB.Model(&system.Role{}).Where("uuid in ?", roleUUIDList).Find(&list)
	return
}

// GetRoleOfUserUUID 通过用户UUID获取其角色列表
func (service *RoleRepository) GetRoleOfUserUUID(uuid string) (userOfRoles []system.UserOfRole) {
	infra.DB.Model(&system.UserOfRole{}).Where("user_uuid = ?", uuid).Find(&userOfRoles)
	return
}

func (service *RoleRepository) List(query cmd.RoleListQry) (roles []system.Role) {
	infra.DB.Find(&roles)
	return
}

func (service *RoleRepository) AddUserOfRole(role system.UserOfRole) error {
	return infra.DB.Create(&role).Error
}

func (service *RoleRepository) DeleteUserOfRole(cmd request.DeleteCmd) error {
	return infra.DB.Where("id in ?", cmd.Ids).Delete(&system.UserOfRole{}).Error
}
