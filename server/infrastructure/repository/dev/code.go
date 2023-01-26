package dev

import (
	"server/client/common/request"
	"server/client/common/response"
	infra "server/infrastructure"
	"server/infrastructure/model/dev"
	"server/util"
)

type CodeRepository struct{}

// Page 查
func (repository *CodeRepository) Page(qry request.PageQuery) (pageResult response.PageResult, err error) {
	databaseName := "devops-platform"
	limit := qry.PageSize
	offset := qry.PageSize * (qry.PageIndex - 1)
	var list []dev.TableInfo
	err = infra.DB.Raw("SELECT TABLE_NAME TableName,CREATE_TIME CreateTime,TABLE_COMMENT TableComment FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? and TABLE_NAME like ? limit ?,?", databaseName, util.Like(qry.Name), offset, limit).Find(&list).Error
	err = infra.DB.Raw("SELECT count(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? and TABLE_NAME like ?", databaseName, util.Like(qry.Name)).Find(&pageResult.Total).Error
	if err != nil {
		return
	}
	pageResult.List = list
	return
}

func (repository *CodeRepository) QueryByTableName(name string) (table dev.Table, err error) {
	err = infra.DB.Raw("select table_name TableName, table_comment Comment from information_schema.tables where table_schema = (select database()) and table_name = ?", name).Find(&table).Error
	err = infra.DB.Raw("select column_name ColumnName, data_type DataType, column_comment Comment, column_key ColumnKey, Extra from information_schema.columns where table_name = ? and table_schema = (select database()) order by ordinal_position", name).Find(&table.Columns).Error
	return
}
