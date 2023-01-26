package cmd

import (
	"github.com/golang-jwt/jwt/v4"
)

type BaseClaims struct {
	UUID        string
	ID          uint
	Username    string
	Name        string
	AuthorityId uint
}

type CustomClaims struct {
	BaseClaims
	BufferTime int64
	jwt.StandardClaims
}
