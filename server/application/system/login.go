package system

import (
	"errors"
	"server/client/system/cmd"
	"server/config"
	infra "server/infrastructure"
	"server/infrastructure/model/system"
	"server/logger"
	"server/util"
)

type LoginServiceApp struct {
}

// Login 处理用户登录
func (p *LoginServiceApp) Login(user cmd.Login) (u system.User, err error) {
	//1.查找数据库用户信息
	err = infra.DB.Where("username = ?", user.Username).First(&u).Error
	if err != nil {
		logger.Log.Error("查询失败")
		return
	}
	//2.对比密码
	pwd, err := util.Base64AESCBCDecrypt(user.Password, []byte(config.GlobalConfig.Encryption.Key))
	if !util.BcryptCheck(string(pwd), u.Password) {
		err = errors.New("密码错误")
	}
	return u, err
}
