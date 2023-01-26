package config

import (
	"flag"
	"fmt"
	"github.com/spf13/viper"
)

var (
	GlobalConfig Config
)

func InitConfig() {
	Load().Unmarshal(&GlobalConfig)
}

// Load 优先级：命令行>环境变量>缺省值
// ./app -c
func Load() *viper.Viper {
	var config string
	flag.StringVar(&config, "c", "", "choose config file.")
	flag.Parse()
	if config == "" { //使用默认配置文件
		config = "config.yaml"
	} else { //使用命令行输入的配置路径文件
		//todo
	}
	v := viper.New()
	v.SetConfigFile(config)
	v.SetConfigType("yaml")
	err := v.ReadInConfig()
	if err != nil {
		panic(fmt.Errorf("Fatal error config file: %s \n", err))
	}
	return v
}
