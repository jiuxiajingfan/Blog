package po

import (
	"blog/model"
	"blog/utils"
)

type Article struct {
	Title     string          `gorm:"type:text;not null" json:"title"`
	Id        uint            `gorm:"primaryKey" json:"id"`
	Descript  string          `gorm:"type:text" json:"desc"`
	Body      string          `gorm:"type:longtext" json:"body"`
	GmtCreate utils.LocalTime `gorm:"autoCreateTime:nano" json:"gmtCreate"`
	GmtUpdate utils.LocalTime `gorm:"autoUpdateTime:nano" json:"gmtUpdate"`
	Label     string          `gorm:"type:varchar(50)" json:"label"`
}

type ArticlePageVO struct {
	Title     string          `json:"title"`
	Id        uint            `json:"id"`
	Descript  string          `json:"desc"`
	GmtCreate utils.LocalTime `json:"gmtCreate"`
	GmtUpdate utils.LocalTime `json:"gmtUpdate"`
	Label     string          `json:"label"`
}

type ArticlePageDTO struct {
	Current  int    `json:"current" `
	PageSize int    `json:"pageSize"`
	Title    string `json:"title"`
	Label    string `json:"label"`
}

func (Article) TableName() string {
	return "t_article"
}

func GetArticlePage(dto ArticlePageDTO) (article []ArticlePageVO, total int64, err error) {
	tx := model.Db.Model(&article).Table("t_article")
	if len(dto.Label) > 0 {
		tx.Where("label = ?", dto.Label)
	}
	if len(dto.Title) > 0 {
		tx.Where("title like", "%"+dto.Title+"%")
	}
	err = tx.Count(&total).Limit(dto.PageSize).Offset(dto.PageSize * (dto.Current - 1)).Find(&article).Error
	return article, total, err
}

func GetArticle(id string) (article Article, err error) {
	tx := model.Db.Model(&article).Table("t_article")
	err = tx.Where("id = ?", id).Find(&article).Error
	return article, err
}
