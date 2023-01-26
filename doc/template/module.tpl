package {{.Module}}

import (
	"server/infrastructure/model"
)

type {{.StructName}} struct {
	model.CommonModel `mapstructure:",squash"`
	{{range $i, $v := .Columns}}
    //{{$v.Comment}}
    {{$v.AttrName}} {{$v.AttrType}} `json:"{{$v.FirstLowName}}",grom:"comment:{{$v.Comment}}"`
    {{end}}
}

func ({{.StructName}}) TableName() string {
	return "{{.TableName}}"
}