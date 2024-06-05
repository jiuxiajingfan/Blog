package model

import (
	"blog/utils"
	"github.com/go-redis/redis"
	"log"
)

// 保存redis连接
var (
	RedisDb *redis.Client
)

// 创建到redis的连接
func init() {

	RedisDb = redis.NewClient(&redis.Options{
		Addr:     utils.RedisHost + ":" + utils.RedisPort,
		Password: utils.RedisPassWord, // no password set
		DB:       utils.RedisDatabase,
		// use default DB
	})
	result, _ := RedisDb.Ping().Result()
	log.Println("Redis ping result:", result)
}
