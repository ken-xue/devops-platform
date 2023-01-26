package system

import (
	"server/application/system/execute"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
	"server/infrastructure/model/system"
)

type RoleServiceApp struct {
}

var RoleApp = new(RoleServiceApp)

// Add 增
func (service *RoleServiceApp) Add(cmd cmd.RoleAddCmd) error {
	return execute.RoleExe.RoleAddCmdExe.Execute(cmd)
}

// Delete 删
func (service *RoleServiceApp) Delete(ids request.DeleteCmd) error {
	return execute.RoleExe.RoleDeleteCmdExe.Execute(ids)
}

// Update 改
func (service *RoleServiceApp) Update(cmd cmd.RoleUpdateCmd) (err error) {
	return execute.RoleExe.RoleUpdateCmdExe.Execute(cmd)
}

// Page 查
func (service *RoleServiceApp) Page(query request.PageQuery) (pageResult response.PageResult, err error) {
	pageResult, err = execute.RoleExe.RolePageQryExe.Execute(query)
	return
}

func (service *RoleServiceApp) List(qry cmd.RoleListQry) (roles []system.Role, err error) {
	return execute.RoleExe.RoleListQryExe.Execute(qry)
}

func (service *RoleServiceApp) Info(qry cmd.RoleInfoQry) (role system.Role, err error) {
	return execute.RoleExe.RoleInfoQryExe.Execute(qry)
}
