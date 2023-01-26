package main

import (
	"fmt"
	"github.com/fvbock/endless"
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
	_ "net/http/pprof"
	"runtime"
	"server/adapter"
	"server/config"
	_ "server/docs"
	"server/initialize"
	"time"
)

func main() {
	pprof()
	initialize.Init()
	run(adapter.Router)
}

func run(router *gin.Engine) {
	address := fmt.Sprintf(":%d", config.GlobalConfig.App.Port)
	s := endless.NewServer(address, router)
	s.ReadHeaderTimeout = 20 * time.Second
	s.WriteTimeout = 20 * time.Second
	s.MaxHeaderBytes = 1 << 20
	s.ListenAndServe()
}

func pprof() {
	go func() {
		runtime.SetBlockProfileRate(1)     // 开启对阻塞操作的跟踪，block
		runtime.SetMutexProfileFraction(1) // 开启对锁调用的跟踪，mutex
		log.Println(http.ListenAndServe(":6060", nil))
	}()
}
