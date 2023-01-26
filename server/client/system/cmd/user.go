package cmd

import "server/client/common/cmd"

type UserAddCmd struct {
	cmd.Cmd
	Username   string   `json:"username"`
	Name       string   `json:"name"`
	Password   string   `json:"password"`
	Email      string   `json:"email"`
	RoleIdList []string `json:"roleIdList"`
}

type UserUpdateCmd struct {
	cmd.Cmd
	Id         int      `json:"id"`
	UUID       string   `json:"uuid"`
	RoleIdList []string `json:"roleIdList"`
	Name       string   `json:"name"`
	Password   string   `json:"password"`
	Email      string   `json:"email"`
	Status     int      `json:"status"`
	Avatar     string   `json:"avatar"`
}

type UserInfoQry struct {
	Id uint `json:"id" form:"id"`
}
