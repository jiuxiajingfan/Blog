package api

import (
	"blog/common"
	"blog/model"
	"github.com/gin-gonic/gin"
)

func GetGuest(c *gin.Context) {
	result, err := model.RedisDb.BitCount("Guest", nil).Result()
	if err == nil {
		common.Ok(c, result)
	} else {
		common.Fail(c, "获取失败")
	}
}
