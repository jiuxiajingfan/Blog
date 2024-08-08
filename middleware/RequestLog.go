package middleware

import (
	"fmt"
	"github.com/gin-gonic/gin"
)

func LoggerMiddleware() gin.HandlerFunc {
	return func(c *gin.Context) {
		// 打印请求信息
		reqBody, _ := c.GetRawData()
		fmt.Printf("[INFO] Request: %s %s %s\n", c.Request.Method, c.Request.RequestURI, reqBody)

		// 执行请求处理程序和其他中间件函数
		c.Next()
	}
}
