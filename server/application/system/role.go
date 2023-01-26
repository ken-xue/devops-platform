package system

import (
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
	infra "server/infrastructure"
	"server/infrastructure/model/system"
)

type RoleServiceApp struct {
}

// Insert 增
func (service *RoleServiceApp) Insert(role system.Role) error {
	//设置基本信息
	role.Create("")
	return infra.DB.Create(&role).Error
}

// Delete 删
func (service *RoleServiceApp) Delete(cmd request.DeleteCmd) error {
	var role system.Role
	return infra.DB.Where("id in ?", cmd.Ids).Delete(&role).Error
}

// Update 改
func (service *RoleServiceApp) Update(role system.Role) (err error) {
	return infra.DB.Where("id = ?", role.ID).First(&system.Role{}).Updates(&role).Error
}

// Page 查
func (service *RoleServiceApp) Page(query request.PageQuery) (pageResult response.PageResult, err error) {
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

func (service *RoleServiceApp) GetById() (role system.Role, err error) {
	return
}

// GetByUserUUID 通过用户UUID获取其角色列表
func (service *RoleServiceApp) GetByUserUUID(uuid string) (list []system.Role) {
	//查找对应的角色
	var roleUUIDList []string
	infra.DB.Model(&system.UserOfRole{}).Where("user_uuid = ?", uuid).Pluck("role_uuid", &roleUUIDList)
	//查找角色对应的权限
	infra.DB.Model(&system.Role{}).Where("uuid in ?", roleUUIDList).Find(&list)
	return
}

func (service *RoleServiceApp) List(query cmd.RoleListQry) (roles []system.Role) {
	infra.DB.Find(&roles)
	return
}

func (service *RoleServiceApp) Info(qry cmd.RoleInfoQry) (role system.Role, err error) {
	err = infra.DB.Where("id = ?", qry.Id).First(&role).Error
	//menuList Todo
	return
}
