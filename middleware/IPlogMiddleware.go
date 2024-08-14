package middleware

import (
	"blog/model"
	"blog/model/po"
	"hash/fnv"
	"math"
	"time"

	"github.com/gin-gonic/gin"
)

// IPLoggerMiddleware 是一个记录用户 IP 地址的中间件
func IPLoggerMiddleware() gin.HandlerFunc {
	return func(c *gin.Context) {
		// 获取用户 IP 地址
		clientIP := c.ClientIP()
		// 获取请求路径
		path := c.Request.URL.Path

		h := fnv.New32a() // 使用FNV-1a哈希算法
		h.Write([]byte(clientIP))
		model.RedisDb.SetBit("Guest", int64(h.Sum32()&math.MaxInt32), 1)

		// 记录请求时间
		startTime := time.Now()

		// 继续处理请求
		c.Next()

		// 请求处理完毕后计算耗时
		latency := time.Since(startTime)
		po.InsertRecord(clientIP, path, latency)
	}
}
