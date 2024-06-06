package common

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

type Response struct {
	Code int         `json:"code"`
	Msg  string      `json:"msg"`
	Data interface{} `json:"data"`
}

type Page struct {
	Records interface{} `json:"records"`
	Total   int64       `json:"total"`
	Size    int         `json:"size"`
	Current int         `json:"current"`
	Pages   int         `json:"pages"`
}

func Result(c *gin.Context, code int, msg string, data interface{}) {
	c.JSON(http.StatusOK, Response{
		code,
		msg,
		data,
	})
}
func Error(code int, msg string) Response {
	return Response{
		code,
		msg,
		nil,
	}
}

func Ok(c *gin.Context, data interface{}) {
	Result(c, 200, "success", data)
	c.Abort()
}
func OkNoData(c *gin.Context, msg string) {
	Result(c, 200, msg, nil)
}

func Fail(c *gin.Context, msg string) {
	Result(c, 400, msg, nil)
	c.Abort()
}
