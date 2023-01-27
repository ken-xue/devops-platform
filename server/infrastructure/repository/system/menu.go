package system

import (
	"server/client/common/request"
	"server/client/common/response"
	infra "server/infrastructure"
	"server/infrastructure/model/system"
)

type MenuRepository struct {
}

var MenuRepo = new(MenuRepository)

// Insert 增
func (service *MenuRepository) Insert(menu system.Menu) error {
	//设置基本信息
	menu.Create("")
	return infra.DB.Create(&menu).Error
}

// Delete 删
func (service *MenuRepository) Delete(cmd request.DeleteCmd) error {
	var menu system.Menu
	return infra.DB.Where("id in ?", cmd.Ids).Delete(&menu).Error
}

// Update 改
func (service *MenuRepository) Update(menu system.Menu) (err error) {
	return infra.DB.Where("id = ?", menu.Id).First(&system.Menu{}).Updates(&menu).Error
}

// Page 查
func (service *MenuRepository) Page(qry request.PageQuery) (pageResult response.PageResult, err error) {
	limit := qry.PageSize
	offset := qry.PageSize * (qry.PageIndex - 1)
	db := infra.DB.Model(&system.Menu{})
	var list []system.Menu
	err = db.Count(&pageResult.Total).Error
	if err != nil {
		return
	}
	err = db.Limit(limit).Offset(offset).Find(&list).Error
	pageResult.List = list
	return
}

// GetByUserUUID 通过用户UUID获取其角色列表
func (service *MenuRepository) GetByUserUUID(uuid string) (list []system.Menu) {
	infra.DB.Raw(`select sm.* from sys_user_of_role suor 
    left join sys_role sr on user_uuid = ? and suor.role_uuid = sr.uuid 
    left join sys_role_of_menu srom on sr.uuid = srom.role_uuid 
    left join sys_menu sm on srom.menu_uuid = sm.uuid where sm.id is not null`, uuid).Find(&list)
	return
}

// GetByRoleUUID 通过角色UUID查询对应菜单
func (service *MenuRepository) GetByRoleUUID(uuid string) (list []system.Menu) {
	infra.DB.Raw(`select sm.* from sys_role sr left join sys_role_of_menu srom on sr.uuid = ? and sr.uuid = srom.role_uuid left join sys_menu sm on sm.uuid = srom.menu_uuid`, uuid).Find(&list)
	return
}
