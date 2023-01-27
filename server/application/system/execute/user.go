package execute

import (
	"github.com/mitchellh/mapstructure"
	"server/client/common/request"
	"server/client/common/response"
	"server/client/system/cmd"
	"server/infrastructure/model/system"
	repo "server/infrastructure/repository/system"
	"server/logger"
	"server/util"
)

// UserInfoQryExe 详情
type UserInfoQryExe struct{}

func (p *UserInfoQryExe) Execute(qry cmd.UserInfoQry) (user system.User, err error) {
	user, _ = repo.UserRepo.Info(qry)
	user.Password = "" //隐藏密码
	roles := repo.RoleRepo.GetByUserUuid(user.UUID)
	for _, role := range roles {
		user.RoleIdList = append(user.RoleIdList, role.UUID)
	}
	return
}

// UserPageQryExe 分页查询
type UserPageQryExe struct{}

func (p *UserPageQryExe) Execute(qry request.PageQuery) (pageResult response.PageResult, err error) {
	return repo.UserRepo.Page(qry)
}

// UserUpdateCmdExe 更新
type UserUpdateCmdExe struct{}

func (p *UserUpdateCmdExe) Execute(cmd cmd.UserUpdateCmd) (err error) {
	user := system.User{}
	mapstructure.Decode(cmd, &user)
	//判断是否需要修改密码
	if user.Password != "" && len(user.Password) > 0 {
		user.Password = util.BcryptHash(user.Password)
	}
	//更新对应的角色
	userOfRoles, _ := repo.UserOfRoleRepo.GetUserOfRoleByUserUuid(user.UUID)
	//已有的角色
	hasMap := make(map[string]system.UserOfRole)
	delMap := make(map[string]system.UserOfRole)
	for _, userOfRole := range userOfRoles {
		hasMap[userOfRole.RoleUUID] = userOfRole
		delMap[userOfRole.RoleUUID] = userOfRole
	}
	//新增的
	for _, uuid := range user.RoleIdList {
		delete(delMap, uuid)
		if _, ok := hasMap[uuid]; !ok {
			userOfRole := system.UserOfRole{
				RoleUUID: uuid,
				UserUUID: user.UUID,
			}
			userOfRole.Create(cmd.Ops)
			err = repo.UserOfRoleRepo.AddUserOfRole(userOfRole)
			if err != nil {
				logger.Log.Error(err.Error())
			}
		}
	}
	//需要删除的旧角色
	deleteIds := make([]uint, 0)
	for _, role := range delMap {
		deleteIds = append(deleteIds, role.Id)
	}
	repo.UserOfRoleRepo.DeleteUserOfRole(request.DeleteCmd{
		Ids: deleteIds,
	})
	return repo.UserRepo.Update(user)
}

// UserAddCmdExe 新增
type UserAddCmdExe struct{}

func (p *UserAddCmdExe) Execute(cmd cmd.UserAddCmd) (err error) {
	//设置基本信息
	user := system.User{}
	mapstructure.Decode(cmd, &user)
	user.Password = util.BcryptHash(cmd.Password)
	user.Create(cmd.Ops)
	//对应的角色
	for _, uuid := range user.RoleIdList {
		userOfRole := system.UserOfRole{
			RoleUUID: uuid,
			UserUUID: user.UUID,
		}
		userOfRole.Create(cmd.Ops)
		err = repo.UserOfRoleRepo.AddUserOfRole(userOfRole)
		if err != nil {
			logger.Log.Error(err.Error())
		}
	}
	return repo.UserRepo.Add(user)
}

// UserDeleteCmdExe 删除
type UserDeleteCmdExe struct{}

func (p *UserDeleteCmdExe) Execute(ids request.DeleteCmd) (err error) {
	return repo.UserRepo.Delete(ids)
}

type UserExeGroup struct {
	UserAddCmdExe
	UserPageQryExe
	UserInfoQryExe
	UserDeleteCmdExe
	UserUpdateCmdExe
}

var UserExe = new(UserExeGroup)
