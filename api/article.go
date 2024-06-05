package api

import (
	"blog/common"
	"blog/constant"
	"blog/model"
	"blog/model/po"
	"encoding/json"
	"github.com/gin-gonic/gin"
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
	} else {
		common.Error(400, err.Error())
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
			common.Ok(c, article)
		} else {
			common.Fail(c, err.Error())
		}
	} else {
		article := po.Article{}
		_ = json.Unmarshal([]byte(result), &article)
		common.Ok(c, article)
	}
}

func GetArticleTime(c *gin.Context) {
	time := po.GetArticleTime()
	common.Ok(c, time)
}

func AddArticle(context *gin.Context) {
	var data po.Article
	_ = context.ShouldBindJSON(&data)
	if data.Title == "" {
		common.Fail(context, "标题不能为空")
	}
	if data.Label == "" {
		common.Fail(context, "标签不能为空")
	}
	if data.Body == "" {
		common.Fail(context, "内容不能为空")
	}
	if data.Descript == "" {
		common.Fail(context, "描述不能为空")
	}
	po.AddArticle(data)
	common.Ok(context, "添加成功")
}
