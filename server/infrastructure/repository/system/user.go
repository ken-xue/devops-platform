package system

import (
	uuid "github.com/satori/go.uuid"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
	infra "server/infrastructure"
	"server/infrastructure/model/system"
	"server/util"
)

type UserRepository struct{}

var UserRepo = new(UserRepository)

// Add 增
func (repository *UserRepository) Add(user system.User) error {
	//设置基本信息
	return infra.DB.Create(&user).Error
}

// Delete 删
func (repository *UserRepository) Delete(cmd request.DeleteCmd) error {
	return infra.DB.Where("id in ?", cmd.Ids).Delete(&system.User{}).Error
}

// Update 改
func (repository *UserRepository) Update(user system.User) (err error) {
	return infra.DB.Where("uuid = ?", user.UUID).First(&system.User{}).Updates(&user).Error
}

// Page 查
func (repository *UserRepository) Page(qry request.PageQuery) (pageResult response.PageResult, err error) {
	limit := qry.PageSize
	offset := qry.PageSize * (qry.PageIndex - 1)
	db := infra.DB.Model(&system.User{}).Where("deleted", false)
	if qry.Name != "" {
		db.Where("username like ?", util.Like(qry.Name))
	}
	var userList []system.User
	err = db.Count(&pageResult.Total).Error
	if err != nil {
		return
	}
	err = db.Limit(limit).Offset(offset).Find(&userList).Error
	pageResult.List = userList
	return
}

// Info 详情
func (repository *UserRepository) Info(qry cmd.UserInfoQry) (user system.User, err error) {
	infra.DB.Where("id = ?", qry.Id).First(&user)
	return
}

func (repository *UserRepository) GetByUUID(uuid uuid.UUID) (user system.User, err error) {
	//同时需要查询对应的角色和权限
	err = infra.DB.Where("uuid = ?", uuid).First(&user).Error
	return
}
