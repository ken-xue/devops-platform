package {{.Module}}

import (
	"server/infrastructure/model/{{.Module}}"
)

// {{.StructName}}Domain 领域对象
type {{.StructName}}Domain struct {
	{{.Module}}.{{.StructName}}
}
