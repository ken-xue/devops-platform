package cmd

type CodeCreateCmd struct {
	TableNames []string `json:"tableNames"`
	CreateType int      `json:"createType"`
	Module     string   `json:"module"`
}
