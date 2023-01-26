package api

import (
	"server/adapter/api/dev"
	"server/adapter/api/system"
)

type GroupAdapter struct {
	SysAdapterGroup system.SysAdapterGroup
	DevAdapterGroup dev.DevAdapterGroup
}

var AdapterGroup = new(GroupAdapter)
