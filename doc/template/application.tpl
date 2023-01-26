package {{.Module}}

import (
	"server/application/{{.Module}}/execute"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/{{.Module}}/cmd"
	"server/infrastructure/model/{{.Module}}"
)

type {{.StructName}}ServiceApp struct{}

var {{.StructName}}App = new({{.StructName}}ServiceApp)

// Add 增
func (service *{{.StructName}}ServiceApp) Add(cmd cmd.{{.StructName}}AddCmd) error {
	return execute.{{.StructName}}Exe.{{.StructName}}AddCmdExe.Execute(cmd)
}

// Delete 删
func (service *{{.StructName}}ServiceApp) Delete(ids request.DeleteCmd) error {
	return execute.{{.StructName}}Exe.{{.StructName}}DeleteCmdExe.Execute(ids)
}

// Update 改
func (service *{{.StructName}}ServiceApp) Update(cmd cmd.{{.StructName}}UpdateCmd) (err error) {
	return execute.{{.StructName}}Exe.{{.StructName}}UpdateCmdExe.Execute(cmd)
}

// Page 查
func (service *{{.StructName}}ServiceApp) Page(query request.PageQuery) (pageResult response.PageResult, err error) {
	pageResult, err = execute.{{.StructName}}Exe.{{.StructName}}PageQryExe.Execute(query)
	return
}

func (service *{{.StructName}}ServiceApp) Info(qry cmd.{{.StructName}}InfoQry) (ret {{.Module}}.{{.StructName}}, err error) {
	return execute.{{.StructName}}Exe.{{.StructName}}InfoQryExe.Execute(qry)
}
