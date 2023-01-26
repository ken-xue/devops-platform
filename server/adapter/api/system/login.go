package system

import (
	"context"
	"github.com/gin-gonic/gin"
	"github.com/go-playground/validator/v10"
	"github.com/mojocn/base64Captcha"
	"go.uber.org/zap"
	"server/client/common/response"
	"server/client/system/cmd"
	"server/config"
	infra "server/infrastructure"
	"server/infrastructure/model/system"
	"server/logger"
	"server/util"
)

type SysLoginAdapter struct{}

var store = util.NewDefaultRedisStore()

// Captcha
// @Tags     登录模块
// @Summary  验证码
// @Success  200   {object}  response.Response{data=cmd.CaptchaResponse,msg=string}
// @Router   /captcha [get]
func (p *SysLoginAdapter) Captcha(c *gin.Context) {
	driver := base64Captcha.NewDriverDigit(config.GlobalConfig.Captcha.ImgHeight, config.GlobalConfig.Captcha.ImgWidth, config.GlobalConfig.Captcha.KeyLong, 0.7, 80)
	cp := base64Captcha.NewCaptcha(driver, store.UseWithCtx(c))
	id, b64s, err := cp.Generate()
	if err != nil {
		response.FailWithMessage("验证码获取失败", c)
		return
	}
	captcha := cmd.CaptchaResponse{
		CaptchaId:     id,
		PicPath:       b64s,
		CaptchaLength: config.GlobalConfig.Captcha.KeyLong,
	}
	response.OkWithDetailed(captcha, "验证码获取成功", c)
}

// Login
// @Tags     登录模块
// @Summary  用户登录
// @Produce   application/json
// @Param    data  body      cmd.Login     true  "用户名, 密码, 验证码"
// @Success  200   {object}  response.Response{data=cmd.LoginResponse,msg=string}  "返回包括用户信息,token,过期时间"
// @Router   /login [post]
func (p *SysLoginAdapter) Login(c *gin.Context) {
	//绑定参数
	var user cmd.Login
	err := c.ShouldBindJSON(&user)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	//验证参数
	validate := validator.New()
	err = validate.Struct(user)
	if err != nil {
		response.FailWithMessage(err.Error(), c)
		return
	}
	//验证码
	if !store.Verify(user.CaptchaId, user.Captcha, true) {
		response.OkWithMessage("验证码错误", c)
		return
	}
	//执行登录
	var u system.User
	u, err = loginServiceApp.Login(user)
	if err != nil {
		response.FailWithMessage("用户名不存在或者密码错误", c)
		return
	}
	//生成token
	p.tokenNext(c, u)
}

// TokenNext 登录以后签发jwt
func (p *SysLoginAdapter) tokenNext(c *gin.Context, user system.User) {
	j := &util.JWT{SigningKey: []byte(config.GlobalConfig.JWT.SigningKey)} // 唯一签名
	claims := j.CreateClaims(cmd.BaseClaims{
		UUID:     user.UUID,
		ID:       user.ID,
		Username: user.Username,
		Name:     user.Name,
	})
	token, err := j.CreateToken(claims)
	if err != nil {
		logger.Log.Error("获取token失败", zap.Error(err))
		response.FailWithMessage("获取token失败", c)
		return
	}
	c.Header("authorization", token)
	response.OkWithMessage("登录成功", c)
}

// Logout
// @Tags     登录模块
// @Summary  退出登录
// @Success  200   {object}  response.Response
// @Router   /logout [get]
func (p *SysLoginAdapter) Logout(c *gin.Context) {
	uuid := util.GetUserUuid(c)
	infra.Redis.Del(context.Background(), uuid)
	response.Ok(c)
}
