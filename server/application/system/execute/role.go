package execute

import (
	bus "github.com/ken-xue/event-bus"
	"github.com/mitchellh/mapstructure"
	"server/application/system/event"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
	"server/infrastructure/model/system"
	repo "server/infrastructure/repository/system"
	"server/logger"
)

// RoleInfoQryExe 详情
type RoleInfoQryExe struct{}

func (p *RoleInfoQryExe) Execute(qry cmd.RoleInfoQry) (info system.Role, err error) {
	info, _ = repo.RoleRepo.Info(qry)
	//设置对应的角色
	info.MenuList = repo.MenuRepo.GetByRoleUUID(info.UUID)
	return
}

// RolePageQryExe 分页查询
type RolePageQryExe struct{}

func (p *RolePageQryExe) Execute(qry request.PageQuery) (pageResult response.PageResult, err error) {
	return repo.RoleRepo.Page(qry)
}

// RoleUpdateCmdExe 更新
type RoleUpdateCmdExe struct{}

func (p *RoleUpdateCmdExe) Execute(cmd cmd.RoleUpdateCmd) (err error) {
	info := system.Role{}
	mapstructure.Decode(cmd, &info)
	info.Id = cmd.Cmd.Id
	info.Update(cmd.Ops)
	err = repo.RoleRepo.Update(info)
	resp := bus.EventBus.Publish(event.RoleUpdateEvent{
		Role: info,
		Cmd:  cmd,
	})
	if resp.Error != nil {
		logger.Log.Error(err.Error())
		err = resp.Error
		return
	}
	return
}

// RoleAddCmdExe 新增
type RoleAddCmdExe struct{}

func (p *RoleAddCmdExe) Execute(cmd cmd.RoleAddCmd) (err error) {
	//设置基本信息
	info := system.Role{}
	mapstructure.Decode(cmd, &info)
	info.Create(cmd.Ops)
	resp := bus.EventBus.Publish(event.RoleAddEvent{MenuList: cmd.MenuList, RoleUuid: info.UUID})
	if resp.Error != nil {
		logger.Log.Error(err.Error())
		err = resp.Error
		return
	}
	err = repo.RoleRepo.Add(info)
	return
}

// RoleDeleteCmdExe 删除
type RoleDeleteCmdExe struct{}

func (p *RoleDeleteCmdExe) Execute(ids request.DeleteCmd) (err error) {
	resp := bus.EventBus.Publish(event.RoleDeleteEvent{Ids: ids.Ids})
	if resp.Error != nil {
		logger.Log.Error(resp.Error.Error())
		err = resp.Error
		return
	}
	return repo.RoleRepo.Delete(ids)
}

// RoleListQryExe 删除
type RoleListQryExe struct{}

func (p *RoleListQryExe) Execute(cmd cmd.RoleListQry) (roles []system.Role, err error) {
	return repo.RoleRepo.List(cmd)
}

type RoleExeGroup struct {
	RoleAddCmdExe
	RolePageQryExe
	RoleInfoQryExe
	RoleListQryExe
	RoleDeleteCmdExe
	RoleUpdateCmdExe
}

var RoleExe = new(RoleExeGroup)
