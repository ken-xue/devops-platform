package system

import (
	"server/infrastructure/model/system"
)

// UserDomain 领域对象
type UserDomain struct {
	system.User
}

func (u *UserDomain) ability() {
	//do something
}
