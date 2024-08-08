package api

import (
	"blog/common"
	"blog/model"
	"blog/model/dto"
	"blog/model/po"
	"github.com/gin-gonic/gin"
	"golang.org/x/crypto/bcrypt"
)

func GetMessage(c *gin.Context) {
	vo := po.GetMessage()
	common.Ok(c, vo)
}

func Login(c *gin.Context) {
	var LoginDTO dto.LoginDTO
	_ = c.ShouldBindJSON(&LoginDTO)
	if LoginDTO.Username == "" {
		common.Fail(c, "用户名不能为空")
		return
	}
	if LoginDTO.Password == "" {
		common.Fail(c, "密码不能为空")
		return
	}
	user := po.FindUser(LoginDTO)
	if user.Name != "" {
		err := bcrypt.CompareHashAndPassword([]byte(user.Password), []byte(LoginDTO.Password))
		if err != nil {
			common.Fail(c, "密码错误")
			return
		} else {
			token, _ := model.GenToken(user.Name)
			common.Ok(c, token)
		}
	} else {
		common.Fail(c, "密码错误")
		return
	}
}

func ChangePic(c *gin.Context) {
	var ChangeDTO dto.ChangeDTO
	_ = c.ShouldBindJSON(&ChangeDTO)
	if ChangeDTO.Back == nil {
		ChangeDTO.Back = []string{}
	}
	username, _ := c.Get("username")
	po.ChangePic(ChangeDTO, username)
	common.Ok(c, "更新成功")
}

func ChangeMessage(c *gin.Context) {
	var ChangeDTO dto.ChangeDTO
	_ = c.ShouldBindJSON(&ChangeDTO)
	username, _ := c.Get("username")
	po.ChangeMessage(ChangeDTO, username)
	common.Ok(c, "更新成功")
}
