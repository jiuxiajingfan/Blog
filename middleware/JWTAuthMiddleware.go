package middleware

import (
	"blog/common"
	"blog/model"
	"errors"
	"github.com/dgrijalva/jwt-go"
	"github.com/gin-gonic/gin"
	"strings"
	"time"
)

const TokenExpireDuration = time.Hour * 2

var Secret = []byte("123456")

// JWTAuthMiddleware 基于JWT的认证中间件
func JWTAuthMiddleware() func(c *gin.Context) {
	return func(c *gin.Context) {
		authHeader := c.GetHeader("Authorization")
		if authHeader == "" {
			common.NoAuth(c)
			return
		}
		parts := strings.SplitN(authHeader, " ", 2)
		if !(len(parts) == 2 && parts[0] == "Bearer") {
			common.NoAuth(c)
			return
		}
		mc, err := ParseToken(parts[1])
		if err != nil {
			common.NoAuth(c)
			return
		}
		c.Set("username", mc.Username)
		// 后续的处理函数可以用过c.Get("username")来获取当前请求的用户信息
		c.Next()
	}
}

// ParseToken 解析JWT
func ParseToken(tokenString string) (*model.JwtToken, error) {
	// 解析token
	token, err := jwt.ParseWithClaims(tokenString, &model.JwtToken{}, func(token *jwt.Token) (i interface{}, err error) {
		return Secret, nil
	})
	if err != nil {
		return nil, err
	}
	if claims, ok := token.Claims.(*model.JwtToken); ok && token.Valid { // 校验token
		return claims, nil
	}
	return nil, errors.New("invalid token")
}
