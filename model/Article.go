package model

import (
	"time"
)

type Article struct {
	Title     string    `gorm:"type:text;not null" json:"title"`
	Id        uint      `gorm:"primaryKey" json:"id"`
	Descript  string    `gorm:"type:text" json:"desc"`
	Body      string    `gorm:"type:longtext" json:"body"`
	GmtCreate time.Time `gorm:"autoCreateTime:nano" json:"gmtCreate"`
	GmtUpdate time.Time `gorm:"autoUpdateTime:nano" json:"gmtUpdate"`
	Label     string    `gorm:"type:varchar(50)" json:"label"`
}

func (Article) TableName() string {
	return "t_article"
}

func GetArticle() ([]Article, error) {
	var article []Article
	err := db.Find(&article).Error
	return article, err
}
