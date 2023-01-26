package application

import (
	"server/application/dev"
	"server/application/system"
)

type ServiceAppGroup struct {
	SystemServiceAppGroup system.ServiceAppGroup
	DevServiceAppGroup    dev.ServiceAppGroup
}

var ServiceGroupApp = new(ServiceAppGroup)
