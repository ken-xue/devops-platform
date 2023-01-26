package util

import (
	"testing"
)

func TestBase64AESCBCEncrypt(t *testing.T) {
	s, err := Base64AESCBCEncrypt([]byte("admin"), []byte("1234567890123456"))
	if err != nil {
		t.Error(err)
	}
	t.Log(s)
}

func Test(t *testing.T) {
	s, err := Base64AESCBCDecrypt("CinA5MJWDvBTvOJSvluE4g==", []byte("1234567890123456"))
	//s, err := Base64AESCBCDecrypt("CinA5MJWDvBTvOJSvluE4g==", []byte("1234567890123456"))
	if err != nil {
		t.Error(err)
	}
	t.Log(string(s))
}
