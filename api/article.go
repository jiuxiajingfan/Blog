package api

import (
	"blog/common"
	"blog/model/po"
	"github.com/gin-gonic/gin"
	"net/http"
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
	c.String(http.StatusOK, "hello World!")
}

func GetArticle(c *gin.Context) {
	id := c.Query("id")
	if id == "" {
		common.Fail(c, "id不能为空！")
	}
	article, err := po.GetArticle(id)
	if err == nil {
		common.Ok(c, article)
	} else {
		common.Fail(c, err.Error())
	}
}
