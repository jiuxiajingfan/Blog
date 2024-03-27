package api

import (
	"blog/model"
	"github.com/gin-gonic/gin"
	"net/http"
)

func GetArticlePage(c *gin.Context) {
	article, err := model.GetArticle()
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
