package dev

import "server/application"

type DevAdapterGroup struct {
	CodeAdapter
}

var (
	codeServiceApp = application.ServiceGroupApp.DevServiceAppGroup.CodeServiceApp
)
