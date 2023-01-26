package execute

import (
	"github.com/mitchellh/mapstructure"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/{{.Module}}/cmd"
	"server/infrastructure/model/{{.Module}}"
	"server/infrastructure/repository"
	repo "server/infrastructure/repository/{{.Module}}"
)

type {{.StructName}}ExeGroup struct {
	{{.StructName}}AddCmdExe
	{{.StructName}}PageQryExe
	{{.StructName}}InfoQryExe
	{{.StructName}}DeleteCmdExe
	{{.StructName}}UpdateCmdExe
}

var {{.StructName}}Exe = new({{.StructName}}ExeGroup)

// {{.StructName}}InfoQryExe 详情
type {{.StructName}}InfoQryExe struct{}

func (p *{{.StructName}}InfoQryExe) Execute(qry cmd.{{.StructName}}InfoQry) (info {{.Module}}.{{.StructName}}, err error) {
	info, _ = repo.{{.StructName}}Repo.Info(qry)
	return
}

// {{.StructName}}PageQryExe 分页查询
type {{.StructName}}PageQryExe struct{}

func (p *{{.StructName}}PageQryExe) Execute(qry request.PageQuery) (pageResult response.PageResult, err error) {
	return repo.{{.StructName}}Repo.Page(qry)
}

// {{.StructName}}UpdateCmdExe 更新
type {{.StructName}}UpdateCmdExe struct{}

func (p *{{.StructName}}UpdateCmdExe) Execute(cmd cmd.{{.StructName}}UpdateCmd) (err error) {
	info := {{.Module}}.{{.StructName}}{}
	mapstructure.Decode(cmd, &info)
	return repo.{{.StructName}}Repo.Update(info)
}

// {{.StructName}}AddCmdExe 新增
type {{.StructName}}AddCmdExe struct{}

func (p *{{.StructName}}AddCmdExe) Execute(cmd cmd.{{.StructName}}AddCmd) (err error) {
	//设置基本信息
	info := {{.Module}}.{{.StructName}}{}
	mapstructure.Decode(cmd, &info)
	info.Create(cmd.Ops)
	return repo.{{.StructName}}Repo.Add(info)
}

// {{.StructName}}DeleteCmdExe 删除
type {{.StructName}}DeleteCmdExe struct{}

func (p *{{.StructName}}DeleteCmdExe) Execute(ids request.DeleteCmd) (err error) {
	return repo.{{.StructName}}Repo.Delete(ids)
}

