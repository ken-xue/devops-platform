package util

import (
	"context"
	"github.com/mojocn/base64Captcha"
	"go.uber.org/zap"
	infra "server/infrastructure"
	"server/logger"
	"time"
)

func NewDefaultRedisStore() *RedisStore {
	return &RedisStore{
		Expiration: time.Second * 180,
		PreKey:     "CAPTCHA_",
	}
}

type RedisStore struct {
	Expiration time.Duration
	PreKey     string
	Context    context.Context
}

func (rs *RedisStore) UseWithCtx(ctx context.Context) base64Captcha.Store {
	rs.Context = ctx
	return rs
}

func (rs *RedisStore) Set(id string, value string) error {
	err := infra.Redis.Set(rs.Context, rs.PreKey+id, value, rs.Expiration).Err()
	if err != nil {
		logger.Log.Error("RedisStoreSetError!", zap.Error(err))
	}
	return err
}

func (rs *RedisStore) Get(key string, clear bool) string {
	val, err := infra.Redis.Get(rs.Context, key).Result()
	if err != nil {
		logger.Log.Error("RedisStoreGetError!", zap.Error(err))
		return ""
	}
	if clear {
		err := infra.Redis.Del(rs.Context, key).Err()
		if err != nil {
			logger.Log.Error("RedisStoreClearError!", zap.Error(err))
			return ""
		}
	}
	return val
}

func (rs *RedisStore) Verify(id, answer string, clear bool) bool {
	key := rs.PreKey + id
	v := rs.Get(key, clear)
	return v == answer
}
