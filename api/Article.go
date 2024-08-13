package api

import (
	"blog/common"
	"blog/constant"
	"blog/model"
	"blog/model/po"
	"encoding/json"
	"github.com/gin-gonic/gin"
	"github.com/jinzhu/copier"
	log "github.com/sirupsen/logrus"
	"strconv"
)

func GetArticlePage(c *gin.Context) {
	var data po.ArticlePageDTO
	_ = c.ShouldBindJSON(&data)
	if data.Current <= 0 {
		data.Current = 1
	}
	if data.PageSize <= 0 {
		data.PageSize = 10
	}
	article, total, err := po.GetArticlePage(data)
	if err == nil {
		common.Ok(c, common.Page{
			Records: article,
			Total:   total,
			Size:    data.PageSize,
			Current: data.Current,
		})
		return
	} else {
		common.Error(400, err.Error())
		return
	}
}

func GetLabel(c *gin.Context) {
	labelVo := po.GetLabel()
	common.Ok(c, labelVo)
}

func GetArticle(c *gin.Context) {
	id := c.Query("id")
	if id == "" {
		common.Fail(c, "id不能为空！")
	}
	result, err1 := model.RedisDb.Get(constant.ARTICLE_KEY + id).Result()
	if err1 != nil {
		article, err := po.GetArticle(id)
		if err == nil {
			data, _ := json.Marshal(article)
			_ = model.RedisDb.Set(constant.ARTICLE_KEY+id, string(data), 0).Err()
			log.Info("缓存中获取文章失败，已写入，id=", id)
			common.Ok(c, article)
			return
		} else {
			common.Fail(c, err.Error())
			return
		}
	} else {
		article := po.Article{}
		_ = json.Unmarshal([]byte(result), &article)
		log.Info("缓存中获取文章成功，id=", id)
		common.Ok(c, article)
		return
	}
}

func GetArticleTime(c *gin.Context) {
	time := po.GetArticleTime()
	common.Ok(c, time)
}

func AddArticle(c *gin.Context) {
	var data po.Article
	_ = c.ShouldBindJSON(&data)
	if data.Title == "" {
		common.Fail(c, "标题不能为空")
		return
	}
	if data.Label == "" {
		common.Fail(c, "标签不能为空")
		return
	}
	if data.Body == "" {
		common.Fail(c, "内容不能为空")
		return
	}
	if data.Descript == "" {
		common.Fail(c, "描述不能为空")
		return
	}
	po.AddArticle(data)
	common.Ok(c, "添加成功")
}

func UpdateArticle(c *gin.Context) {
	var data po.ArticleDTO
	var article po.Article
	_ = c.ShouldBindJSON(&data)
	copier.Copy(&article, &data)
	parseUint, _ := strconv.ParseInt(data.Id, 10, 64)
	article.Id = parseUint
	if article.Id == 0 {
		common.Fail(c, "id不能为空")
		return
	}
	if article.Title == "" {
		common.Fail(c, "标题不能为空")
		return
	}
	if article.Label == "" {
		common.Fail(c, "标签不能为空")
		return
	}
	if article.Body == "" {
		common.Fail(c, "内容不能为空")
		return
	}
	if article.Descript == "" {
		common.Fail(c, "描述不能为空")
		return
	}
	err := po.UpdateArticle(article)
	if err != nil {
		common.Fail(c, err.Error())
		return
	} else {
		common.Ok(c, "修改成功")
		model.RedisDb.Del(constant.ARTICLE_KEY + data.Id)
		return
	}
}

func DeleteArticle(c *gin.Context) {
	id := c.Query("id")
	if id == "" {
		common.Fail(c, "id不能为空！")
	}
	po.DeleteArticle(id)
	model.RedisDb.Del(constant.ARTICLE_KEY + id)
	common.Ok(c, "删除成功")
	return
}
