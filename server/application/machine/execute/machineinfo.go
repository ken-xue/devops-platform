package execute

import (
	"github.com/mitchellh/mapstructure"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/machine/cmd"
	"server/infrastructure/model/machine"
	"server/infrastructure/repository"
	repo "server/infrastructure/repository/machine"
)

// MachineInfoInfoQryExe 详情
type MachineInfoInfoQryExe struct{}

func (p *MachineInfoInfoQryExe) Execute(qry cmd.MachineInfoInfoQry) (info machine.MachineInfo, err error) {
	info, _ = repo.MachineInfoRepo.Info(qry)
	return
}

// MachineInfoPageQryExe 分页查询
type MachineInfoPageQryExe struct{}

func (p *MachineInfoPageQryExe) Execute(qry request.PageQuery) (pageResult response.PageResult, err error) {
	return repo.MachineInfoRepo.Page(qry)
}

// MachineInfoUpdateCmdExe 更新
type MachineInfoUpdateCmdExe struct{}

func (p *MachineInfoUpdateCmdExe) Execute(cmd cmd.MachineInfoUpdateCmd) (err error) {
	info := machine.MachineInfo{}
	mapstructure.Decode(cmd, &info)
	return repo.MachineInfoRepo.Update(info)
}

// MachineInfoAddCmdExe 新增
type MachineInfoAddCmdExe struct{}

func (p *MachineInfoAddCmdExe) Execute(cmd cmd.MachineInfoAddCmd) (err error) {
	//设置基本信息
	info := machine.MachineInfo{}
	mapstructure.Decode(cmd, &info)
	info.Create(cmd.Ops)
	return repo.MachineInfoRepo.Add(info)
}

// MachineInfoDeleteCmdExe 删除
type MachineInfoDeleteCmdExe struct{}

func (p *MachineInfoDeleteCmdExe) Execute(ids request.DeleteCmd) (err error) {
	return repo.MachineInfoRepo.Delete(ids)
}


type MachineInfoExeGroup struct {
	MachineInfoAddCmdExe
	MachineInfoPageQryExe
	MachineInfoInfoQryExe
	MachineInfoDeleteCmdExe
	MachineInfoUpdateCmdExe
}

var MachineInfoExe = new(MachineInfoExeGroup)
