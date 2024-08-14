package po

import (
	"blog/model"
	"blog/utils"
	"time"
)

type Record struct {
	Id        int64           `gorm:"primaryKey;autoIncrement" json:"id"`
	Ip        string          `gorm:"type:varchar(100)" json:"ip"`
	ApiUrl    string          `gorm:"type:varchar(200)" json:"apiUrl"`
	GmtCreate utils.LocalTime `gorm:"autoCreateTime:nano" json:"gmtCreate"`
	Time      float32         `gorm:"type:decimal(10,6)" json:"time"`
}

func InsertRecord(ip string, path string, latency time.Duration) {
	Record := Record{
		Ip:        ip,
		ApiUrl:    path,
		GmtCreate: utils.LocalTime(time.Now()),
		Time:      float32(latency.Microseconds()) / 1000,
	}
	tx := model.Db.Table("t_record")
	tx.Create(&Record)
}
