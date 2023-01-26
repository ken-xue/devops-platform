package machine

import (
	"server/infrastructure/model"
)

type MachineInfo struct {
	model.CommonModel `mapstructure:",squash"`
	
    //服务器名
    Name string `json:"name",grom:"comment:服务器名"`
    
    //服务器ip
    Ip string `json:"ip",grom:"comment:服务器ip"`
    
    //连接端口
    Port int `json:"port",grom:"comment:连接端口"`
    
    //服务器登陆用户名
    AccessUsername string `json:"accessUsername",grom:"comment:服务器登陆用户名"`
    
    //访问方式
    AccessWay string `json:"accessWay",grom:"comment:访问方式"`
    
    //服务器登陆密码
    AccessPassword string `json:"accessPassword",grom:"comment:服务器登陆密码"`
    
    //服务器登陆密钥
    AccessKey string `json:"accessKey",grom:"comment:服务器登陆密钥"`
    
}

func (MachineInfo) TableName() string {
	return "machine_info"
}