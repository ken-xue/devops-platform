package {{.Module}}

import (
	"github.com/gin-gonic/gin"
	"github.com/go-playground/validator/v10"
	"server/adapter/common"
	"server/application/{{.Module}}"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/{{.Module}}/cmd"
)

type {{.StructName}}Adapter struct {
    common.CommonAdapter
}

var Adapter{{.StructName}} = new({{.StructName}}Adapter)

// Info {{.Comment}}
// @Tags     {{.Comment}}模块
// @Summary  详情{{.Comment}}
// @Produce   application/json
// @Param    data  body      cmd.{{.StructName}}AddCmd     true  "{{.Comment}}信息"
// @Success  200   {object}  response.Response
// @Router   /{{.Module}}/{{.AllLowName}}/info [get]
// Info 详情
func (p *{{.StructName}}Adapter) Info(c *gin.Context) {
	qry := cmd.{{.StructName}}InfoQry{}
	err := c.ShouldBindQuery(&qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	data, err := {{.Module}}.{{.StructName}}App.Info(qry)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(data, c)
}

// Add {{.Comment}}
// @Tags     {{.Comment}}模块
// @Summary  新增{{.Comment}}
// @Produce   application/json
// @Param    data  body      cmd.{{.StructName}}AddCmd     true  "用户信息"
// @Success  200   {object}  response.Response
// @Router   /{{.Module}}/{{.AllLowName}}/add [post]
// Insert 增
func (p *{{.StructName}}Adapter) Add(c *gin.Context) {
	//绑定参数
	var cmd cmd.{{.StructName}}AddCmd
	err := c.ShouldBindJSON(&cmd)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	//验证参数
	validate := validator.New()
	err = validate.Struct(cmd)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	cmd.Ops = p.Ops(c)
	err = {{.Module}}.{{.StructName}}App.Add(cmd)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Delete {{.Comment}}
// @Tags     {{.Comment}}模块
// @Summary  删除{{.Comment}}
// @Produce   application/json
// @Param    data  body      request.DeleteCmd     true  "删除{{.Comment}}"
// @Success  200   {object}  response.Response
// @Router   /{{.Module}}/{{.AllLowName}}/delete [delete]
func (p *{{.StructName}}Adapter) Delete(c *gin.Context) {
	//绑定参数
	var ids request.DeleteCmd
	err := c.ShouldBindJSON(&ids)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	//验证参数
	validate := validator.New()
	err = validate.Struct(ids)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	err = {{.Module}}.{{.StructName}}App.Delete(ids)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Update {{.Comment}}
// @Tags     {{.Comment}}模块
// @Summary  修改{{.Comment}}
// @Produce   application/json
// @Param    data  body      cmd.{{.StructName}}UpdateCmd     true  "修改{{.Comment}}"
// @Success  200   {object}  response.Response
// @Router   /{{.Module}}/{{.AllLowName}}/update [put]
func (p *{{.StructName}}Adapter) Update(c *gin.Context) {
	//绑定参数
	var command cmd.{{.StructName}}UpdateCmd
	err := c.ShouldBindJSON(&command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	//验证参数
	validate := validator.New()
	err = validate.Struct(command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	command.Ops = p.Ops(c)
	err = {{.Module}}.{{.StructName}}App.Update(command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Page {{.Comment}}
// @Tags     {{.Comment}}模块
// @Summary  查询{{.Comment}}
// @Produce   application/json
// @Param    data  query      request.PageQuery    true  "分页参数"
// @Success  200   {object}  response.Response
// @Router   /{{.Module}}/{{.AllLowName}}/page [get]
func (p *{{.StructName}}Adapter) Page(c *gin.Context) {
	//绑定参数
	var page request.PageQuery
	err := c.ShouldBindQuery(&page)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	pageResult, err := {{.Module}}.{{.StructName}}App.Page(page)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(pageResult, c)
}
