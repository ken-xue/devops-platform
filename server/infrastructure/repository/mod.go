package repository

import (
	"server/infrastructure/repository/dev"
	"server/infrastructure/repository/machine"
	"server/infrastructure/repository/pipeline"
	"server/infrastructure/repository/system"
)

type Repository struct {
	system.SysRepositoryGroup
	pipeline.PipelineRepositoryGroup
	machine.MachineRepositoryGroup
	dev.DevRepositoryGroup
}

var Repo = new(Repository)
