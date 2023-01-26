package system

import (
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
	infra "server/infrastructure"
	"server/infrastructure/model/system"
	"strings"
)

type MenuServiceApp struct{}

// Insert 增
func (service *MenuServiceApp) Insert(menu system.Menu) error {
	//设置基本信息
	menu.Create("")
	return infra.DB.Create(&menu).Error
}

// Delete 删
func (service *MenuServiceApp) Delete(cmd request.DeleteCmd) error {
	var menu system.Menu
	return infra.DB.Where("id in ?", cmd.Ids).Delete(&menu).Error
}

// Update 改
func (service *MenuServiceApp) Update(menu system.Menu) (err error) {
	return infra.DB.Where("id = ?", menu.ID).First(&system.Menu{}).Updates(&menu).Error
}

// Page 查
func (service *MenuServiceApp) Page(qry request.PageQuery) (pageResult response.PageResult, err error) {
	limit := qry.PageSize
	offset := qry.PageSize * (qry.PageIndex - 1)
	db := infra.DB.Model(&system.Menu{}).Where("(parent_uuid = ? and type = ?) or type = ? or parent_uuid is null", 0, 0, 0)
	var list []system.Menu
	err = db.Count(&pageResult.Total).Error
	if err != nil {
		return
	}
	err = db.Limit(limit).Offset(offset).Find(&list).Error
	checkHasChild(&list)
	pageResult.List = list
	return
}

// GetByUserUUID 通过用户UUID获取其角色列表
func (service *MenuServiceApp) GetByUserUUID(uuid string) (list []system.Menu) {
	infra.DB.Raw(`select sm.* from sys_user_of_role suor 
    left join sys_role sr on user_uuid = ? and suor.role_uuid = sr.uuid 
    left join sys_role_of_menu srom on sr.uuid = srom.role_uuid 
    left join sys_menu sm on srom.menu_uuid = sm.uuid where sm.id is not null`, uuid).Find(&list)
	return
}

func (service *MenuServiceApp) List(qry cmd.MenuListQry) (list []system.Menu, err error) {
	err = infra.DB.Where("parent_uuid = ?", qry.ParentUuid).Find(&list).Error
	checkHasChild(&list)
	return
}

func (service *MenuServiceApp) Select(qry cmd.MenuSelectQry) (list []system.Menu, err error) {
	db := infra.DB.Model(&system.Menu{})
	if len(qry.ExcludeTypes) > 0 {
		s := strings.Split(qry.ExcludeTypes, ",")
		db.Where("type not in ?", s)
	}
	if len(qry.IncludeTypes) > 0 {
		s := strings.Split(qry.IncludeTypes, ",")
		db.Where("type in ?", s)
	}
	err = db.Find(&list).Error
	if qry.NeedRoot {
		menu := system.Menu{}
		menu.Name = "一级菜单"
		menu.Type = 0
		menu.UUID = "0"
		list = append(list, menu)
	}
	return
}

func checkHasChild(list *[]system.Menu) {
	for i, menu := range *list {
		var cnt int64
		infra.DB.Model(&system.Menu{}).Where("parent_uuid = ?", menu.UUID).Count(&cnt)
		if cnt > 0 {
			menu.HasChildren = true
		}
		(*list)[i] = menu
	}
}
