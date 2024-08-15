package routes

import (
	"blog/api"
	"blog/middleware"
	"blog/utils"
	"github.com/gin-gonic/gin"
	"log"
)

func init() {
	gin.SetMode(gin.ReleaseMode)
	router := gin.Default()
	article := router.Group("article")
	{
		article.POST("/getArticlePage", middleware.IPLoggerMiddleware(), api.GetArticlePage)
		article.POST("/addArticle", middleware.IPLoggerMiddleware(), middleware.JWTAuthMiddleware(), api.AddArticle)
		article.POST("/updateArticle", middleware.IPLoggerMiddleware(), middleware.JWTAuthMiddleware(), api.UpdateArticle)
		article.GET("/getLabel", middleware.IPLoggerMiddleware(), api.GetLabel)
		article.GET("/getArticle", middleware.IPLoggerMiddleware(), api.GetArticle)
		article.GET("/getArticleTime", middleware.IPLoggerMiddleware(), api.GetArticleTime)
		article.GET("/deleteArticle", middleware.IPLoggerMiddleware(), middleware.JWTAuthMiddleware(), api.DeleteArticle)
	}
	record := router.Group("record")
	{
		record.GET("/getGuest", api.GetGuest)
	}
	user := router.Group("user")
	{
		user.GET("/getMessage", middleware.IPLoggerMiddleware(), api.GetMessage)
		user.POST("/login", middleware.IPLoggerMiddleware(), api.Login)
		user.POST("/changePwd", middleware.IPLoggerMiddleware(), middleware.JWTAuthMiddleware(), api.ChangePwd)
		user.POST("/changePic", middleware.IPLoggerMiddleware(), middleware.JWTAuthMiddleware(), api.ChangePic)
		user.POST("/changeMessage", middleware.IPLoggerMiddleware(), middleware.JWTAuthMiddleware(), api.ChangeMessage)
	}
	err := router.Run(utils.HttpPort)
	if err != nil {
		log.Println(err)
	} else {
		log.Println("启动成功")
	}
}
