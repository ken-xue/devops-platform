package logger

import (
	"go.uber.org/zap"
	"go.uber.org/zap/zapcore"
	"os"
)

var (
	Log *zap.Logger
)

func loadZap() (logger *zap.Logger) {
	consoleEncoder := zapcore.NewConsoleEncoder(zap.NewDevelopmentEncoderConfig())
	logger = zap.New(zapcore.NewTee(zapcore.NewCore(consoleEncoder, zapcore.Lock(os.Stdout), zapcore.DebugLevel)))
	logger.WithOptions(zap.AddCaller())
	return logger
}

func InitLogger() {
	Log = loadZap()
}
