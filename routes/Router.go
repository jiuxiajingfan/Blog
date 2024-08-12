package routes

import (
	"blog/api"
	"blog/middleware"
	"blog/utils"
	"github.com/gin-gonic/gin"
	"log"
)

func init() {
	router := gin.Default()
	article := router.Group("article")
	{
		article.POST("/getArticlePage", api.GetArticlePage)
		article.POST("/addArticle", middleware.JWTAuthMiddleware(), api.AddArticle)
		article.POST("/updateArticle", middleware.JWTAuthMiddleware(), api.UpdateArticle)
		article.GET("/getLabel", api.GetLabel)
		article.GET("/getArticle", api.GetArticle)
		article.GET("/getArticleTime", api.GetArticleTime)
		article.GET("/deleteArticle", middleware.JWTAuthMiddleware(), api.DeleteArticle)
	}
	record := router.Group("record")
	{
		record.GET("/getGuest")
	}
	user := router.Group("user")
	{
		user.GET("/getMessage", middleware.JWTAuthMiddleware(), api.GetMessage)
		user.POST("/login", api.Login)
		user.POST("/changePwd", middleware.JWTAuthMiddleware(), api.ChangePwd)
		user.POST("/changePic", middleware.JWTAuthMiddleware(), api.ChangePic)
		user.POST("/changeMessage", middleware.JWTAuthMiddleware(), api.ChangeMessage)
	}
	err := router.Run(utils.HttpPort)
	if err != nil {
		log.Println(err)
	} else {
		log.Println("启动成功")
	}
}
