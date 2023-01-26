package cmd

type MenuListQry struct {
	ParentUuid        string `json:"parentUuid" form:"parentUuid"`
	NeedCheckHasChild bool   `json:"needCheckHasChild" form:"needCheckHasChild"` //是否需要检查有子类
	ExcludeTypes      string `json:"excludeTypes" form:"excludeTypes"`
	IncludeTypes      string `json:"includeTypes" form:"includeTypes"`
	NeedRoot          bool   `json:"needRoot" form:"needRoot"`
}
type MenuSelectQry struct {
	ExcludeTypes string `json:"excludeTypes" form:"excludeTypes"`
	IncludeTypes string `json:"includeTypes" form:"includeTypes"`
	NeedRoot     bool   `json:"needRoot" form:"needRoot"`
}
