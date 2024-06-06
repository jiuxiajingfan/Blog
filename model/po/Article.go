package po

import (
	"blog/model"
	"blog/utils"
	"github.com/bwmarrin/snowflake"
	"sort"
	"strconv"
)

var node *snowflake.Node

type Article struct {
	Title     string          `gorm:"type:text;not null" json:"title"`
	Id        int64           `gorm:"primaryKey;autoIncrement" json:"id"`
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

type LabelVo struct {
	Label string `json:"label"`
	Num   int    `json:"num"`
}

type ArticleTimeVo struct {
	Time string          `json:"time"`
	List []ArticlePageVO `json:"list"`
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
	err = tx.Order("gmt_create desc").Count(&total).Limit(dto.PageSize).Offset(dto.PageSize * (dto.Current - 1)).Find(&article).Error
	return article, total, err
}

func GetArticle(id string) (article Article, err error) {
	tx := model.Db.Model(&article).Table("t_article")
	err = tx.Where("id = ?", id).Find(&article).Error
	return article, err
}

func GetLabel() (labelVo LabelVo) {
	model.Db.Raw(
		"select a.label, count(*) as num " +
			"from t_article a " +
			"group by a.label  " +
			"order by num desc").Scan(&labelVo)
	return labelVo
}

func GetArticleTime() []ArticleTimeVo {
	var articles []ArticlePageVO
	var articleMap = make(map[string][]ArticlePageVO)
	var ans []ArticleTimeVo
	tx := model.Db.Model(&articles).Table("t_article").Order("gmt_create desc")
	tx.Find(&articles)
	for _, article := range articles {
		articleMap[article.GmtCreate.String()[:4]] = append(articleMap[article.GmtCreate.String()[:4]], article)
	}
	for key, value := range articleMap {
		temp := new(ArticleTimeVo)
		temp.Time = key
		temp.List = value
		ans = append(ans, *temp)
	}
	sort.Slice(ans, func(i, j int) bool {
		time1, _ := strconv.Atoi(ans[i].Time)
		time2, _ := strconv.Atoi(ans[j].Time)
		return time1 >= time2
	})
	return ans
}

func AddArticle(data Article) {
	data.Id = node.Generate().Int64()
	tx := model.Db.Table("t_article")
	tx.Create(&data)
}

func init() {
	node, _ = snowflake.NewNode(1)
}

func UpdateArticle(data Article) error {
	err := model.Db.Table("t_article").Model(data).Omit("id").Updates(data).Error
	return err
}

func DeleteArticle(id string) {
	model.Db.Table("t_article").Where("id = ?", id).Delete(&Article{})
}
