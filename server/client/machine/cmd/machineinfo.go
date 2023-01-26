package cmd

import "server/client/common/cmd"

type MachineInfoAddCmd struct {
	cmd.Cmd
    
    //服务器名
	Name string `json:"name"`
	
    //服务器ip
	Ip string `json:"ip"`
	
    //连接端口
	Port int `json:"port"`
	
    //服务器登陆用户名
	AccessUsername string `json:"accessUsername"`
	
    //访问方式
	AccessWay string `json:"accessWay"`
	
    //服务器登陆密码
	AccessPassword string `json:"accessPassword"`
	
    //服务器登陆密钥
	AccessKey string `json:"accessKey"`
	
}

type MachineInfoUpdateCmd struct {
	cmd.Cmd
	
    //服务器名
	Name string `json:"name"`
	
    //服务器ip
	Ip string `json:"ip"`
	
    //连接端口
	Port int `json:"port"`
	
    //服务器登陆用户名
	AccessUsername string `json:"accessUsername"`
	
    //访问方式
	AccessWay string `json:"accessWay"`
	
    //服务器登陆密码
	AccessPassword string `json:"accessPassword"`
	
    //服务器登陆密钥
	AccessKey string `json:"accessKey"`
	
}

type MachineInfoInfoQry struct {
	Id uint `json:"id" form:"id"`
}
