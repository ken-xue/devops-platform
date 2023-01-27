package cmd

type Cmd struct {
	Id  uint `json:"id" form:"id"`
	Ops string
}
