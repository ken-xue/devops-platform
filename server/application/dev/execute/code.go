package execute

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/dev/cmd"
	"server/infrastructure/model/dev"
	"server/infrastructure/repository"
	"server/logger"
	"server/util"
	"strings"
	"text/template"
)

type CodeCreateInnerCmdExe struct{}

func convert(t *dev.Table) {
	for i := 0; i < len(t.Columns); i++ {
		key := t.Columns[i].DataType
		if _, ok := typeMappingMap[key]; !ok {
			logger.Log.Info(fmt.Sprintf("key : %s no exist", key))
		} else {
			t.Columns[i].AttrType = typeMappingMap[key]
			t.Columns[i].AttrName = util.Case2Camel(t.Columns[i].ColumnName)
			t.Columns[i].FirstLowName = strings.ToLower(t.Columns[i].AttrName[:1]) + t.Columns[i].AttrName[1:]
		}
		t.StructName = util.Case2Camel(t.TableName)
		t.AllLowName = strings.ToLower(t.StructName)
		t.FirstLowName = strings.ToLower(t.StructName[:1]) + t.StructName[1:]
	}
}

func (p *CodeCreateInnerCmdExe) Execute(c cmd.CodeCreateCmd) (response any, err error) {
	storePaths := make([]string, len(c.TableNames)*len(templates))
	for _, name := range c.TableNames {
		table, _ := repository.Repo.CodeRepository.QueryByTableName(name)
		table.Module = c.Module
		remove(&table)
		convert(&table)
		logger.Log.Info(fmt.Sprintf("%#v", table))
		for tplPath, storePath := range templates {
			tpl := template.Must(template.ParseGlob(tplPath))
			storePath = strings.ReplaceAll(storePath, "{module}", c.Module)
			storePath = strings.ReplaceAll(storePath, "{table}", table.AllLowName)
			if strings.Index(tplPath, "index") > -1 || strings.Index(tplPath, "api") > -1 || strings.Index(tplPath, "menu.tpl") > -1 {
				storePath = "../" + storePath
			}
			folder := storePath[:strings.LastIndex(storePath, "/")]
			filePath := storePath
			util.CreateFolder(folder)
			file, err := os.OpenFile(filePath, os.O_CREATE|os.O_WRONLY, os.ModePerm)
			if err != nil {
				logger.Log.Error(err.Error())
				continue
			}
			err = tpl.ExecuteTemplate(file, tpl.Name(), table)
			//添加到依赖
			if err != nil {
				logger.Log.Error(err.Error())
				continue
			}
			file.Close()
			storePaths = append(storePaths, filePath)
		}
	}
	file, err := os.OpenFile(lastCreateCode, os.O_CREATE|os.O_WRONLY, os.ModePerm)
	if err != nil {
		logger.Log.Error(err.Error())
		return
	}
	defer file.Close()
	for _, path := range storePaths {
		file.WriteString(path + "\n")
	}
	return
}

type CodeCreateDownloadCmdExe struct{}

func (p *CodeCreateDownloadCmdExe) Execute(c cmd.CodeCreateCmd) (response any, err error) {
	prefix := "code/"
	files := make([]string, 0)
	defer func() {
		err := os.RemoveAll(prefix)
		if err != nil {
			logger.Log.Error(err.Error())
		}
	}()
	for _, name := range c.TableNames {
		table, _ := repository.Repo.CodeRepository.QueryByTableName(name)
		table.Module = c.Module
		//remove common field
		remove(&table)
		convert(&table)
		logger.Log.Info(fmt.Sprintf("%#v", table))
		for tplPath, storePath := range templates {
			storePath = prefix + storePath
			tpl := template.Must(template.ParseGlob(tplPath))
			storePath = strings.ReplaceAll(storePath, "{module}", c.Module)
			storePath = strings.ReplaceAll(storePath, "{table}", table.AllLowName)
			folder := storePath[:strings.LastIndex(storePath, "/")]
			filePath := storePath
			util.CreateFolder(folder)
			file, err := os.OpenFile(filePath, os.O_CREATE|os.O_WRONLY, os.ModePerm)
			if err != nil {
				logger.Log.Error(err.Error())
				continue
			}
			tpl.ExecuteTemplate(file, tpl.Name(), table)
			files = append(files, filePath)
		}
	}
	util.ZipFiles("code.zip", files, ".", ".")
	return
}

func remove(t *dev.Table) {
	arr := make([]dev.Column, 0)
	for _, c := range t.Columns {
		if _, ok := excludeFieldsMap[c.ColumnName]; ok {
			continue
		}
		arr = append(arr, c)
	}
	t.Columns = arr
}

type TablePageQryExe struct{}

func (p *TablePageQryExe) Execute(qry request.PageQuery) (response.PageResult, error) {
	page, err := repository.Repo.CodeRepository.Page(qry)
	if err != nil {
		logger.Log.Error(err.Error())
	}
	return page, err
}

type DeleteCodeCmdExe struct{}

func (p *DeleteCodeCmdExe) Execute() (err error) {
	file, err := os.OpenFile(lastCreateCode, os.O_RDONLY, os.ModePerm)
	if err != nil {
		logger.Log.Error(err.Error())
	}
	reader := bufio.NewReader(file)
	for {
		line, _, err := reader.ReadLine()
		if err == io.EOF {
			break
		}
		f := string(line)
		if f == "" {
			continue
		}
		os.Remove(f)
	}
	return
}

//生成的文件存放路径

var templates = map[string]string{
	"../doc/template/adapter.tpl":     "adapter/api/{module}/{table}.go",
	"../doc/template/router.tpl":      "adapter/router/{module}/{table}.go",
	"../doc/template/module.tpl":      "infrastructure/model/{module}/{table}.go",
	"../doc/template/repository.tpl":  "infrastructure/repository/{module}/{table}.go",
	"../doc/template/cmd.tpl":         "client/{module}/cmd/{table}.go",
	"../doc/template/application.tpl": "application/{module}/{table}.go",
	"../doc/template/assembler.tpl":   "application/{module}/assembler/{table}.go",
	"../doc/template/execute.tpl":     "application/{module}/execute/{table}.go",
	"../doc/template/event.tpl":       "application/{module}/event/{table}.go",
	"../doc/template/handler.tpl":     "application/{module}/handler/{table}.go",
	"../doc/template/domain.tpl":      "domain/{module}/{table}.go",
	//前端
	"../doc/template/index.tpl": "web/src/views/{module}/{table}/index.vue",
	"../doc/template/api.tpl":   "web/src/api/{module}/{table}.js",
	"../doc/template/menu.tpl":  "doc/script/{module}/{table}.sql",
}

var excludeFieldsMap = map[string]string{
	"id":           "",
	"uuid":         "",
	"creator":      "",
	"modifier":     "",
	"gmt_create":   "",
	"gmt_modified": "",
	"deleted":      "",
}

var typeMappingMap = map[string]string{
	"tinyint":        "int8",
	"smallint":       "int16",
	"mediumint":      "int32",
	"int":            "int",
	"integer":        "int",
	"bigint":         "int64",
	"float":          "float32",
	"double":         "float64",
	"decimal":        "BigDecimal",
	"bit":            "bool",
	"char":           "string",
	"varchar":        "string",
	"tinytext":       "string",
	"text":           "string",
	"mediumtext":     "string",
	"longtext":       "string",
	"date":           "string",
	"datetime":       "string",
	"timestamp":      "string",
	"NUMBER":         "int",
	"INT":            "int",
	"INTEGER":        "int",
	"BINARY_INTEGER": "int",
	"LONG":           "string",
	"FLOAT":          "float32",
	"BINARY_FLOAT":   "float32",
	"DOUBLE":         "float64",
	"BINARY_DOUBLE":  "float64",
	"DECIMAL":        "float64",
	"CHAR":           "string",
	"VARCHAR":        "string",
	"VARCHAR2":       "string",
	"NVARCHAR":       "string",
	"NVARCHAR2":      "string",
	"CLOB":           "string",
	"BLOB":           "string",
	"DATE":           "string",
	"DATETIME":       "string",
	"TIMESTAMP":      "string",
	"TIMESTAMP(6)":   "string",
	"int8":           "int8",
	"int4":           "int8",
	"int2":           "int8",
	"numeric":        "float64",
	"nvarchar":       "string",
}

var lastCreateCode = "/tmp/last_create_code.txt"
