package assembler

import (
	"github.com/mitchellh/mapstructure"
	domain "server/domain/{{.Module}}"
	"server/infrastructure/model/{{.Module}}"
)

// M2D Module to Domain
func M2D(m {{.Module}}.{{.StructName}}) (d domain.{{.StructName}}Domain) {
	mapstructure.Decode(m, &d)
	return
}

// D2M Domain to Module
func D2M(d domain.{{.StructName}}Domain) (m {{.Module}}.{{.StructName}}) {
	mapstructure.Decode(d, &m)
	return
}
