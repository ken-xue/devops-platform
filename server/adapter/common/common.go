package common

import "github.com/gin-gonic/gin"

type CommonAdapter struct {
}

func (receiver *CommonAdapter) Ops(c *gin.Context) string {
	return c.GetString("username")
}
