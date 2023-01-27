package handler

import (
	"errors"
	"fmt"
	bus "github.com/ken-xue/event-bus"
	ev "server/application/system/event"
	"server/client/common/request"
	"server/infrastructure/model/system"
	repo "server/infrastructure/repository/system"
	"server/logger"
)

type RoleAddEventHandler struct {
}

func (p RoleAddEventHandler) Execute(event bus.Event) (re bus.Response) {
	e := event.(ev.RoleAddEvent)
	logger.Log.Info(fmt.Sprintf("event : %#v", e))
	for _, menu := range e.MenuList {
		repo.RoleOfMenuRepo.AddRoleOfMenu(system.RoleOfMenu{
			RoleUUID: e.RoleUuid,
			MenuUUID: menu,
		})
	}
	return
}

type RoleDeleteEventHandler struct {
}

func (p RoleDeleteEventHandler) Execute(event bus.Event) (re bus.Response) {
	e := event.(ev.RoleDeleteEvent)
	logger.Log.Info(fmt.Sprintf("event : %#v", e))
	roles, err := repo.RoleRepo.GetById(e.Ids)
	if err != nil {
		logger.Log.Error(fmt.Sprintf("handler error : %s", err))
	}
	for _, role := range roles {
		//如果该角色还关联人，不给予删除
		if has, _ := repo.UserOfRoleRepo.HasUserByRoleUuid(role.UUID); has > 0 {
			return bus.Response{}
		}
		repo.RoleOfMenuRepo.DeleteRoleOfMenu(role.UUID)
	}
	return
}

type RoleUpdateEventHandler struct {
}

func (p RoleUpdateEventHandler) Execute(event bus.Event) (resp bus.Response) {
	e := event.(ev.RoleUpdateEvent)
	logger.Log.Info(fmt.Sprintf("event : %#v", e))
	//角色更新需要更新对应的菜单
	roles, err := repo.RoleRepo.GetById([]uint{e.Role.Id})
	if len(roles) == 0 {
		resp.Error = errors.New(fmt.Sprintf("not found role record id is : %v", e.Role.Id))
		return
	}
	role := roles[0]
	//先查询出原有的
	roleOfMenus, err := repo.RoleOfMenuRepo.GetRoleOfMenuByRoleUuid(role.UUID)
	if err != nil {
		logger.Log.Error(err.Error())
		resp.Error = err
		return
	}
	Has := make(map[string]system.RoleOfMenu)
	NeedDel := make(map[string]uint)
	for _, menu := range roleOfMenus {
		Has[menu.MenuUUID] = menu
		NeedDel[menu.MenuUUID] = menu.Id
	}
	NeedAdd := make(map[string]system.RoleOfMenu)
	for _, menu := range e.Role.MenuList {
		delete(NeedDel, menu.UUID)
		if _, ok := Has[menu.UUID]; ok {
			continue
		}
		NeedAdd[menu.UUID] = system.RoleOfMenu{
			RoleUUID: e.Role.UUID,
			MenuUUID: menu.UUID,
		}
	}
	//新增的添加
	for _, roleOfMenu := range NeedAdd {
		repo.RoleOfMenuRepo.AddRoleOfMenu(roleOfMenu)
	}
	//多于的删除
	var Ids []uint
	for _, id := range NeedDel {
		Ids = append(Ids, id)
	}
	repo.UserOfRoleRepo.DeleteUserOfRole(request.DeleteCmd{
		Ids: Ids,
	})
	return
}
