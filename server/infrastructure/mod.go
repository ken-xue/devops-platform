package infra

import (
	"context"
	"fmt"
	"github.com/go-redis/redis/v8"
	"github.com/minio/minio-go/v7"
	"github.com/minio/minio-go/v7/pkg/credentials"
	"go.uber.org/zap"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
	glog "gorm.io/gorm/logger"
	"log"
	"server/config"
	"server/logger"
)

var (
	DB    *gorm.DB
	Redis *redis.Client
	Minio *minio.Client
)

func loadDB() *gorm.DB {
	dsn := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s?charset=utf8mb4&parseTime=True&loc=Local",
		config.GlobalConfig.DataBase.Username,
		config.GlobalConfig.DataBase.Password,
		config.GlobalConfig.DataBase.Host,
		config.GlobalConfig.DataBase.Port,
		config.GlobalConfig.DataBase.Name)
	db, err := gorm.Open(mysql.New(mysql.Config{
		DSN:                       dsn,   // data source name
		DefaultStringSize:         256,   // default size for string fields
		DisableDatetimePrecision:  true,  // disable datetime precision, which not supported before MySQL 5.6
		DontSupportRenameIndex:    true,  // drop & create when rename index, rename index not supported before MySQL 5.7, MariaDB
		DontSupportRenameColumn:   true,  // `change` when rename column, rename column not supported before MySQL 8, MariaDB
		SkipInitializeWithVersion: false, // autoconfigure based on currently MySQL version
	}), &gorm.Config{
		Logger: glog.Default.LogMode(glog.Info),
	})
	if err != nil {
		panic(fmt.Errorf("Fatal error connection database: %s \n", err))
	}
	return db
}

func loadMinio() *minio.Client {
	config := config.GlobalConfig.Minio
	// Initialize minio client object.
	minio, err := minio.New(config.Endpoint, &minio.Options{
		Creds:  credentials.NewStaticV4(config.AccessKeyID, config.SecretAccessKey, ""),
		Secure: config.UseSSL,
	})
	if err != nil {
		logger.Log.Fatal(err.Error())
	}
	log.Printf("%#v\n", minio) // minioClient is now set up
	return minio
}

func loadRedis() *redis.Client {
	redisCfg := config.GlobalConfig.Redis
	client := redis.NewClient(&redis.Options{
		Addr:     redisCfg.Addr,
		Password: redisCfg.Password, // no password set
		DB:       redisCfg.DB,       // use default DB
	})
	pong, err := client.Ping(context.Background()).Result()
	if err != nil {
		logger.Log.Error("redis connect ping failed, err:", zap.Error(err))
	} else {
		logger.Log.Info("redis connect ping response:", zap.String("pong", pong))
	}
	return client
}

func InitInfra() {
	DB = loadDB()
	Redis = loadRedis()
	Minio = loadMinio()
}
