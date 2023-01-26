package dev

import "server/application/dev/execute"

type ServiceAppGroup struct {
	CodeServiceApp
}

var (
	CodeCreateInnerCmdExe    = new(execute.CodeCreateInnerCmdExe)
	CodeCreateDownloadCmdExe = new(execute.CodeCreateDownloadCmdExe)
	TablePageQryExe          = new(execute.TablePageQryExe)
	DeleteCodeCmdExe         = new(execute.DeleteCodeCmdExe)
)
