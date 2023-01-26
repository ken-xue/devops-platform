package cmd

type MenuListQry struct {
	ParentUuid string `json:"parentUuid" form:"parentUuid"`
}
type MenuSelectQry struct {
	ExcludeTypes string `json:"excludeTypes" form:"excludeTypes"`
	IncludeTypes string `json:"includeTypes" form:"includeTypes"`
	NeedRoot     bool   `json:"needRoot" form:"needRoot"`
}
