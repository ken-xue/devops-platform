package system

import (
	"server/application/system/execute"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
	"server/infrastructure/model/system"
)

type UserServiceApp struct{}

// Add 增
func (service *UserServiceApp) Add(cmd cmd.UserAddCmd) error {
	return execute.UserExe.UserAddCmdExe.Execute(cmd)
}

// Delete 删
func (service *UserServiceApp) Delete(ids request.DeleteCmd) error {
	return execute.UserExe.UserDeleteCmdExe.Execute(ids)
}

// Update 改
func (service *UserServiceApp) Update(cmd cmd.UserUpdateCmd) (err error) {
	return execute.UserExe.UserUpdateCmdExe.Execute(cmd)
}

// Page 查
func (service *UserServiceApp) Page(query request.PageQuery) (pageResult response.PageResult, err error) {
	pageResult, err = execute.UserExe.UserPageQryExe.Execute(query)
	return
}

func (service *UserServiceApp) Info(qry cmd.UserInfoQry) (ret system.User, err error) {
	return execute.UserExe.UserInfoQryExe.Execute(qry)
}
