package system

import (
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
	infra "server/infrastructure"
	"server/infrastructure/model/system"
	"server/util"
)

type RoleRepository struct{}

var RoleRepo = new(RoleRepository)

// Add 增
func (service *RoleRepository) Add(role system.Role) error {
	return infra.DB.Create(&role).Error
}

// Delete 删
func (service *RoleRepository) Delete(cmd request.DeleteCmd) error {
	return infra.DB.Where("id in ?", cmd.Ids).Delete(&system.Role{}).Error
}

// Update 改
func (service *RoleRepository) Update(role system.Role) (err error) {
	return infra.DB.Where("id = ?", role.Id).First(&system.Role{}).Updates(&role).Error
}

// Page 查
func (service *RoleRepository) Page(query request.PageQuery) (pageResult response.PageResult, err error) {
	limit := query.PageSize
	offset := query.PageSize * (query.PageIndex - 1)
	db := infra.DB.Model(&system.Role{})
	if len(query.Name) > 0 {
		db.Where("name like ?", util.Like(query.Name))
	}
	var list []system.Role
	err = db.Count(&pageResult.Total).Error
	if err != nil {
		return
	}
	err = db.Limit(limit).Offset(offset).Find(&list).Error
	pageResult.List = list
	return
}

func (service *RoleRepository) GetById(ids []uint) (roles []system.Role, err error) {
	err = infra.DB.Where("id in ?", ids).Find(&roles).Error
	return
}

// GetByUserUuid 通过用户UUID获取其角色列表
func (service *RoleRepository) GetByUserUuid(uuid string) (list []system.Role) {
	//查找对应的角色
	userOfRoles, _ := UserOfRoleRepo.GetUserOfRoleByUserUuid(uuid)
	roleUUIDList := make([]string, 0)
	for _, role := range userOfRoles {
		roleUUIDList = append(roleUUIDList, role.RoleUUID)
	}
	//查找角色对应的权限
	infra.DB.Model(&system.Role{}).Where("uuid in ?", roleUUIDList).Find(&list)
	return
}

func (service *RoleRepository) List(query cmd.RoleListQry) (roles []system.Role, err error) {
	err = infra.DB.Find(&roles).Error
	return
}

// Info 详情
func (service *RoleRepository) Info(qry cmd.RoleInfoQry) (info system.Role, err error) {
	infra.DB.Where("id = ?", qry.Id).First(&info)
	return
}
