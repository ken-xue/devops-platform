package assembler

import (
	"github.com/mitchellh/mapstructure"
	domain "server/domain/machine"
	"server/infrastructure/model/machine"
)

// M2D Module to Domain
func M2D(m machine.MachineInfo) (d domain.MachineInfoDomain) {
	mapstructure.Decode(m, &d)
	return
}

// D2M Domain to Module
func D2M(d domain.MachineInfoDomain) (m machine.MachineInfo) {
	mapstructure.Decode(d, &m)
	return
}
