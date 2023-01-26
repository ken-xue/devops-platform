package dev

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"os"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/dev/cmd"
)

type CodeAdapter struct {
}

// Page Table
// @Tags     工具模块
// @Summary  查询数据库表
// @Produce   application/json
// @Param    data  query     request.PageQuery     true  "分页参数"
// @Success  200   {object}  response.Response
// @Router   /dev/code/page [get]
func (p *CodeAdapter) Page(c *gin.Context) {
	//绑定参数
	var page request.PageQuery
	err := c.ShouldBindQuery(&page)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	pageResult, err := codeServiceApp.Page(page)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.OkWithData(pageResult, c)
}

// Create Table
// @Tags     工具模块
// @Summary  生成代码
// @Produce   application/json
// @Param    data  body      cmd.CodeCreateCmd     true  "用户信息"
// @Success  200   {object}  response.Response
// @Router   /dev/code/create [post]
func (p *CodeAdapter) Create(c *gin.Context) {
	//绑定参数
	var command cmd.CodeCreateCmd
	err := c.ShouldBindJSON(&command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	//内部创建
	_, err = codeServiceApp.CreateInner(command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	response.Ok(c)
}

// Download Table
// @Tags     工具模块
// @Summary  生成代码
// @Produce   application/json
// @Param    data  body      cmd.CodeCreateCmd 		true 	"生成代码"
// @Success  200   {object}  response.Response
// @Router   /dev/code/download [post]
func (p *CodeAdapter) Download(c *gin.Context) {
	//绑定参数
	var command cmd.CodeCreateCmd
	err := c.ShouldBindJSON(&command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	//下载
	_, err = codeServiceApp.CreateDownload(command)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
	} else {
		c.Writer.Header().Add("Content-Disposition", fmt.Sprintf("attachment; filename=%s", "code.zip"))
		c.File("./code.zip")
		os.Remove("./code.zip")
	}
}

// Delete Table
// @Tags     工具模块
// @Summary  删除最近一次生成的代码
// @Produce   application/json
// @Success  200   {object}  response.Response
// @Router   /dev/code/delete [delete]
func (p *CodeAdapter) Delete(c *gin.Context) {
	err := codeServiceApp.Delete()
	if err != nil {
		response.FailWithMessage(err.Error(), c)
	} else {
		response.Ok(c)
	}
}
