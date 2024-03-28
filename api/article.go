package api

import (
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
	article, err := po.GetArticle(data)
	if err == nil {
		c.JSON(200, gin.H{
			"status":  200,
			"data":    article,
			"message": err,
		},
		)
	}
}

func GetLabel(c *gin.Context) {
	c.String(http.StatusOK, "hello World!")
}
