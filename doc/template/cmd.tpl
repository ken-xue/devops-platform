package cmd

import "server/client/common/cmd"

type {{.StructName}}AddCmd struct {
	cmd.Cmd
    {{range $i, $v := .Columns}}
    //{{$v.Comment}}
	{{$v.AttrName}} {{$v.AttrType}} `json:"{{$v.FirstLowName}}"`
	{{end}}
}

type {{.StructName}}UpdateCmd struct {
	cmd.Cmd
	{{range $i, $v := .Columns}}
    //{{$v.Comment}}
	{{$v.AttrName}} {{$v.AttrType}} `json:"{{$v.FirstLowName}}"`
	{{end}}
}

type {{.StructName}}InfoQry struct {
	Id uint `json:"id" form:"id"`
}
