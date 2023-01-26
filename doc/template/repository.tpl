package {{.Module}}

import (
	"server/client/common/request"
	"server/client/common/response"
	"server/client/{{.Module}}/cmd"
	infra "server/infrastructure"
	"server/infrastructure/model/{{.Module}}"
	"server/util"
)

type {{.StructName}}Repository struct{}

var {{.StructName}}Repo = new({{.StructName}}Repository)

// Add 增
func (repository *{{.StructName}}Repository) Add(info {{.Module}}.{{.StructName}}) error {
	//设置基本信息
	return infra.DB.Create(&info).Error
}

// Delete 删
func (repository *{{.StructName}}Repository) Delete(cmd request.DeleteCmd) error {
	return infra.DB.Where("id in ?", cmd.Ids).Delete(&{{.Module}}.{{.StructName}}{}).Error
}

// Update 改
func (repository *{{.StructName}}Repository) Update(info {{.Module}}.{{.StructName}}) (err error) {
	return infra.DB.Where("uuid = ?", info.UUID).First(&{{.Module}}.{{.StructName}}{}).Updates(&info).Error
}

// Page 查
func (repository *{{.StructName}}Repository) Page(qry request.PageQuery) (pageResult response.PageResult, err error) {
	limit := qry.PageSize
	offset := qry.PageSize * (qry.PageIndex - 1)
	db := infra.DB.Model(&{{.Module}}.{{.StructName}}{}).Where("deleted", false)
	if qry.Name != "" {
		db.Where("name like ?", util.Like(qry.Name))
	}
	var list []{{.Module}}.{{.StructName}}
	err = db.Count(&pageResult.Total).Error
	if err != nil {
		return
	}
	err = db.Limit(limit).Offset(offset).Find(&list).Error
	pageResult.List = list
	return
}

// Info 详情
func (repository *{{.StructName}}Repository) Info(qry cmd.{{.StructName}}InfoQry) (info {{.Module}}.{{.StructName}}, err error) {
	infra.DB.Where("id = ?", qry.Id).First(&info)
	return
}