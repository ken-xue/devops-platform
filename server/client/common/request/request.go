package request

type PageQuery struct {
	PageIndex int    `json:"pageIndex" form:"pageIndex"` // 页码
	PageSize  int    `json:"pageSize" form:"pageSize"`   // 每页大小
	Name      string `json:"name" form:"name"`           //关键字
	Keyword   string `json:"keyword" form:"keyword"`     //关键字
	Value     string `json:"value" form:"value"`         //关键字值
}

func (p *PageQuery) offset() int {
	return p.PageSize * (p.PageIndex - 1)
}

type DeleteCmd struct {
	Ids []uint `json:"ids" form:"ids"`
}
