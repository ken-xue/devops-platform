package dev

import (
	"server/client/common/request"
	"server/client/common/response"
	"server/client/dev/cmd"
)

type CodeServiceApp struct{}

func (service *CodeServiceApp) CreateDownload(cmd cmd.CodeCreateCmd) (any, error) {
	return CodeCreateDownloadCmdExe.Execute(cmd)
}

func (service *CodeServiceApp) CreateInner(cmd cmd.CodeCreateCmd) (any, error) {
	return CodeCreateInnerCmdExe.Execute(cmd)
}

func (service *CodeServiceApp) Page(qry request.PageQuery) (pageResult response.PageResult, err error) {
	return TablePageQryExe.Execute(qry)
}

func (service *CodeServiceApp) Delete() error {
	return DeleteCodeCmdExe.Execute()
}
