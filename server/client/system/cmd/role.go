package cmd

import "server/client/common/cmd"

type RoleListQry struct {
	name string
}

type RoleAddCmd struct {
	cmd.Cmd
	//角色名
	Name string `json:"name"`
	//备注
	Remark string `json:"remark"`
	//所选菜单
	MenuList []string `json:"menuList"`
}

type RoleUpdateCmd struct {
	cmd.Cmd
	//角色名
	Name string `json:"name"`
	//备注
	Remark string `json:"remark"`
}

type RoleInfoQry struct {
	Id uint `json:"id" form:"id"`
}
