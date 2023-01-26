package handler

import "github.com/ken-xue/event-bus"

type UserAddEventHandler struct {
}

func (receiver UserAddEventHandler) Execute(event event.Event) (resp event.Response) {

	return resp
}

type UserDeleteEventHandler struct {
}

func (receiver UserDeleteEventHandler) Execute(event event.Event) (resp event.Response) {

	return resp
}

type UserUpdateEventHandler struct {
}

func (receiver UserUpdateEventHandler) Execute(event event.Event) (resp event.Response) {

	return resp
}
