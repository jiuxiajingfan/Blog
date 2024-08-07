package model

import (
	"blog/utils"
	"errors"
	"github.com/dgrijalva/jwt-go"
	"time"
)

const TokenExpireDuration = time.Hour * 2

var Secret = utils.JwtSecret

type JwtToken struct {
	Username string `json:"username"`
	jwt.StandardClaims
}

// GenToken 生成JWT
func GenToken(username string) (string, error) {
	// 创建一个我们自己的声明
	claim := JwtToken{
		username, // 自定义字段
		jwt.StandardClaims{
			ExpiresAt: time.Now().Add(TokenExpireDuration).Unix(), // 过期时间
			Issuer:    "blog",                                     // 签发人
		},
	}
	return jwt.NewWithClaims(jwt.SigningMethodHS256, claim).SignedString([]byte(Secret))
}

// ParseToken 解析JWT
func ParseToken(tokenString string) (*JwtToken, error) {
	// 解析token
	token, err := jwt.ParseWithClaims(tokenString, &JwtToken{}, func(token *jwt.Token) (i interface{}, err error) {
		return Secret, nil
	})
	if err != nil {
		return nil, err
	}
	if claims, ok := token.Claims.(*JwtToken); ok && token.Valid { // 校验token
		return claims, nil
	}
	return nil, errors.New("invalid token")
}
