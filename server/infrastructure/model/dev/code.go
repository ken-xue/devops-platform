package dev

import "server/infrastructure/model"

type Column struct {
	//列名
	ColumnName string
	//列名类型
	DataType string
	//列名备注
	Comment string
	//属性名称(第一个字母大写)，如：user_name => UserName
	AttrName string
	//第一个字母小写
	FirstLowName string
	//属性类型
	AttrType string
	//auto_increment
	Extra string
}

type Table struct {
	//表的名称
	TableName string `json:"tableName"`
	//表的备注
	Comment string `json:"comment"`
	//表的列名(不包含主键)
	Columns []Column `json:"columns" gorm:"-"`
	//(第一个字母大写)，如：sys_user => SysUser
	StructName string `json:"structName"`
	//
	FirstLowName string `json:"firstLowName"`
	//(全部小写)，如：sys_user => sysuser
	AllLowName string `json:"allLowName"`
	//模块
	Module string `json:"module"`
}

type TableInfo struct {
	TableName    string
	CreateTime   model.Time
	TableComment string
}
