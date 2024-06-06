package api

import (
	"blog/common"
	"blog/constant"
	"blog/model"
	"blog/model/po"
	"encoding/json"
	"github.com/gin-gonic/gin"
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
			common.Ok(c, article)
			return
		} else {
			common.Fail(c, err.Error())
			return
		}
	} else {
		article := po.Article{}
		_ = json.Unmarshal([]byte(result), &article)
		common.Ok(c, article)
		return
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
		return
	}
	if data.Label == "" {
		common.Fail(context, "标签不能为空")
		return
	}
	if data.Body == "" {
		common.Fail(context, "内容不能为空")
		return
	}
	if data.Descript == "" {
		common.Fail(context, "描述不能为空")
		return
	}
	po.AddArticle(data)
	common.Ok(context, "添加成功")
}

func UpdateArticle(context *gin.Context) {
	var data po.Article
	_ = context.ShouldBindJSON(&data)
	if data.Id == 0 {
		common.Fail(context, "id不能为空")
		return
	}
	if data.Title == "" {
		common.Fail(context, "标题不能为空")
		return
	}
	if data.Label == "" {
		common.Fail(context, "标签不能为空")
		return
	}
	if data.Body == "" {
		common.Fail(context, "内容不能为空")
		return
	}
	if data.Descript == "" {
		common.Fail(context, "描述不能为空")
		return
	}
	err := po.UpdateArticle(data)
	if err != nil {
		common.Fail(context, err.Error())
		return
	} else {
		common.Ok(context, "修改成功")
		model.RedisDb.Del(constant.ARTICLE_KEY + strconv.FormatInt(data.Id, 10))
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
