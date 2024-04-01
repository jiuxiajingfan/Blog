package routes

import (
	"blog/api"
	"github.com/gin-gonic/gin"
	"log"
)

func init() {
	router := gin.Default()
	article := router.Group("article")
	{
		article.POST("/getArticlePage", api.GetArticlePage)
		article.POST("/addArticle")
		article.POST("/updateArticle")
		article.GET("/getLabel", api.GetLabel)
		article.GET("/getArticle", api.GetArticle)
		article.GET("/getArticleTime")
		article.GET("/deleteArticle")
	}
	record := router.Group("record")
	{
		record.GET("/getGuest")
	}
	user := router.Group("user")
	{
		user.GET("/getMessage")
		user.POST("/login")
		user.POST("/changePwd")
		user.POST("/changePic")
		user.POST("/changeMessage")
	}
	err := router.Run(":3641")
	if err != nil {
		log.Println(err)
	} else {
		log.Println("启动成功")
	}
}
