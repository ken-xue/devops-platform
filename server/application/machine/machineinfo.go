package machine

import (
	"server/application/machine/execute"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/machine/cmd"
	"server/infrastructure/model/machine"
)

type MachineInfoServiceApp struct{}

var MachineInfoApp = new(MachineInfoServiceApp)

// Add 增
func (service *MachineInfoServiceApp) Add(cmd cmd.MachineInfoAddCmd) error {
	return execute.MachineInfoExe.MachineInfoAddCmdExe.Execute(cmd)
}

// Delete 删
func (service *MachineInfoServiceApp) Delete(ids request.DeleteCmd) error {
	return execute.MachineInfoExe.MachineInfoDeleteCmdExe.Execute(ids)
}

// Update 改
func (service *MachineInfoServiceApp) Update(cmd cmd.MachineInfoUpdateCmd) (err error) {
	return execute.MachineInfoExe.MachineInfoUpdateCmdExe.Execute(cmd)
}

// Page 查
func (service *MachineInfoServiceApp) Page(query request.PageQuery) (pageResult response.PageResult, err error) {
	pageResult, err = execute.MachineInfoExe.MachineInfoPageQryExe.Execute(query)
	return
}

func (service *MachineInfoServiceApp) Info(qry cmd.MachineInfoInfoQry) (ret machine.MachineInfo, err error) {
	return execute.MachineInfoExe.MachineInfoInfoQryExe.Execute(qry)
}
