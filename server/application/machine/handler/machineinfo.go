package handler

import "github.com/ken-xue/event-bus"

type MachineInfoAddEventHandler struct {
}

func (receiver MachineInfoAddEventHandler) Execute(event event.Event) (resp event.Response) {

	return resp
}

type MachineInfoDeleteEventHandler struct {
}

func (receiver MachineInfoDeleteEventHandler) Execute(event event.Event) (resp event.Response) {

	return resp
}

type MachineInfoUpdateEventHandler struct {
}

func (receiver MachineInfoUpdateEventHandler) Execute(event event.Event) (resp event.Response) {

	return resp
}
