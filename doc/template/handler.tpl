package handler

import "github.com/ken-xue/event-bus"

type {{.StructName}}AddEventHandler struct {
}

func (receiver {{.StructName}}AddEventHandler) Execute(event event.Event) (resp event.Response) {

	return resp
}

type {{.StructName}}DeleteEventHandler struct {
}

func (receiver {{.StructName}}DeleteEventHandler) Execute(event event.Event) (resp event.Response) {

	return resp
}

type {{.StructName}}UpdateEventHandler struct {
}

func (receiver {{.StructName}}UpdateEventHandler) Execute(event event.Event) (resp event.Response) {

	return resp
}
