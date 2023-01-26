package machine

import (
	"server/client/common/request"
	"server/client/common/response"
	"server/client/machine/cmd"
	infra "server/infrastructure"
	"server/infrastructure/model/machine"
	"server/util"
)

type MachineInfoRepository struct{}

var MachineInfoRepo = new(MachineInfoRepository)

// Add 增
func (repository *MachineInfoRepository) Add(info machine.MachineInfo) error {
	//设置基本信息
	return infra.DB.Create(&info).Error
}

// Delete 删
func (repository *MachineInfoRepository) Delete(cmd request.DeleteCmd) error {
	return infra.DB.Where("id in ?", cmd.Ids).Delete(&machine.MachineInfo{}).Error
}

// Update 改
func (repository *MachineInfoRepository) Update(info machine.MachineInfo) (err error) {
	return infra.DB.Where("uuid = ?", info.UUID).First(&machine.MachineInfo{}).Updates(&info).Error
}

// Page 查
func (repository *MachineInfoRepository) Page(qry request.PageQuery) (pageResult response.PageResult, err error) {
	limit := qry.PageSize
	offset := qry.PageSize * (qry.PageIndex - 1)
	db := infra.DB.Model(&machine.MachineInfo{}).Where("deleted", false)
	if qry.Name != "" {
		db.Where("name like ?", util.Like(qry.Name))
	}
	var list []machine.MachineInfo
	err = db.Count(&pageResult.Total).Error
	if err != nil {
		return
	}
	err = db.Limit(limit).Offset(offset).Find(&list).Error
	pageResult.List = list
	return
}

// Info 详情
func (repository *MachineInfoRepository) Info(qry cmd.MachineInfoInfoQry) (info machine.MachineInfo, err error) {
	infra.DB.Where("id = ?", qry.Id).First(&info)
	return
}