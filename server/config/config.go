package config

type Config struct {
	App        App        `yaml:"app"`
	DataBase   DataBase   `yaml:"data_base"`
	Captcha    Captcha    `yaml:"captcha"`
	Encryption Encryption `yaml:"encryption"`
	JWT        JWT        `yaml:"jwt"`
	Redis      Redis      `yaml:"redis"`
	Minio      Minio      `yaml:"minio"`
}

type App struct {
	Name string `yaml:"name"`
	Port int    `yaml:"port"`
}

type DataBase struct {
	Host     string `json:"host" yaml:"host"`         // host
	Port     string `json:"port" yaml:"port"`         //:端口
	Name     string `json:"name" yaml:"name"`         // 数据库名
	Username string `json:"username" yaml:"username"` // 数据库用户名
	Password string `json:"password" yaml:"password"` // 数据库密码
}

type Captcha struct {
	KeyLong   int `mapstructure:"key-long" json:"key-long" yaml:"key-long"`       // 验证码长度
	ImgWidth  int `mapstructure:"img-width" json:"img-width" yaml:"img-width"`    // 验证码宽度
	ImgHeight int `mapstructure:"img-height" json:"img-height" yaml:"img-height"` // 验证码高度
}

type Encryption struct {
	Key string `yaml:"key"`
}

type JWT struct {
	SigningKey  string `mapstructure:"signing-key" json:"signing-key" yaml:"signing-key"`    // jwt签名
	ExpiresTime string `mapstructure:"expires-time" json:"expires-time" yaml:"expires-time"` // 过期时间
	BufferTime  string `mapstructure:"buffer-time" json:"buffer-time" yaml:"buffer-time"`    // 缓冲时间
	Issuer      string `mapstructure:"issuer" json:"issuer" yaml:"issuer"`                   // 签发者
}

type Redis struct {
	DB       int    `mapstructure:"db" json:"db" yaml:"db"`                   // redis的哪个数据库
	Addr     string `mapstructure:"addr" json:"addr" yaml:"addr"`             // 服务器地址:端口
	Password string `mapstructure:"password" json:"password" yaml:"password"` // 密码
}

type Minio struct {
	Endpoint        string `yaml:"endpoint"`
	AccessKeyID     string `yaml:"accessKeyID"`
	SecretAccessKey string `yaml:"secretAccessKey"`
	UseSSL          bool   `yaml:"useSSL"`
}
