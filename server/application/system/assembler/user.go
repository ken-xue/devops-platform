package assembler

import (
	"github.com/mitchellh/mapstructure"
	domain "server/domain/system"
	"server/infrastructure/model/system"
)

// M2D Module to Domain
func M2D(user system.User) (d domain.UserDomain) {
	mapstructure.Decode(user, &d)
	return
}

// D2M Domain to Module
func D2M(d domain.UserDomain) (user system.User) {
	mapstructure.Decode(d, &user)
	return
}
