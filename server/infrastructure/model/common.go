package model

import (
	"database/sql/driver"
	"errors"
	"fmt"
	uuid "github.com/satori/go.uuid"
	"strings"
	"time"
)

type CommonModel struct {
	Id          uint   `json:"id" gorm:"primarykey"` // 主键ID
	UUID        string `json:"uuid" gorm:"index;comment:UUID"`
	GmtCreate   Time   `json:"gmtCreate"`   // 创建时间
	Creator     string `json:"creator"`     // 创建人
	GmtModified Time   `json:"gmtModified"` // 更新时间
	Modifier    string `json:"modifier"`    // 更新人
	Deleted     bool   `json:"deleted"`     // 是否删除
}

func (p *CommonModel) Create(creator string) {
	p.Modifier = creator
	p.Creator = creator
	p.UUID = strings.ReplaceAll(uuid.NewV4().String(), "-", "")
	p.GmtCreate = Time(time.Now())
	p.GmtModified = Time(time.Now())
}

func (p *CommonModel) Update(creator string) {
	p.Modifier = creator
	p.GmtModified = Time(time.Now())
}

func (p *CommonModel) Del(creator string) {
	p.Modifier = creator
	p.GmtModified = Time(time.Now())
	p.Deleted = true
}

var format = "2006-01-02 15:04:05"

type Time time.Time

func (t *Time) UnmarshalJSON(data []byte) error {
	if string(data) == "null" {
		return nil
	}
	var err error
	//前端接收的时间字符串
	str := string(data)
	//去除接收的str收尾多余的"
	timeStr := strings.Trim(str, "\"")
	t1, err := time.Parse(format, timeStr)
	*t = Time(t1)
	return err
}

func (t Time) MarshalJSON() ([]byte, error) {
	formatted := fmt.Sprintf("\"%v\"", time.Time(t).Format(format))
	return []byte(formatted), nil
}

func (t Time) Value() (driver.Value, error) {
	// MyTime 转换成 time.Time 类型
	tTime := time.Time(t)
	return tTime.Format(format), nil
}

func (t *Time) Scan(v interface{}) error {
	switch vt := v.(type) {
	case time.Time:
		// 字符串转成 time.Time 类型
		*t = Time(vt)
	default:
		return errors.New("类型处理错误")
	}
	return nil
}

func (t *Time) String() string {
	return fmt.Sprintf("time : %s", time.Time(*t).String())
}
