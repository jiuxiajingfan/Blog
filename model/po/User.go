package po

import (
	"blog/model"
	"blog/model/dto"
	"blog/utils"
	"encoding/json"
	"github.com/jinzhu/copier"
)

type User struct {
	Id         int64           `gorm:"primaryKey" json:"id"`
	Name       string          `gorm:"type:varchar(20)" json:"name"`
	Password   string          `gorm:"type:varchar(100)" json:"password"`
	Nickname   string          `gorm:"type:varchar(20)" json:"nickname"`
	GmtCreate  utils.LocalTime `gorm:"autoCreateTime:nano" json:"gmtCreate"`
	ImgUrl     string          `gorm:"type:varchar(500)" json:"imgUrl"`
	Github     string          `gorm:"type:varchar(500)" json:"github"`
	Email      string          `gorm:"type:varchar(500)" json:"email"`
	Record     string          `gorm:"type:varchar(100)" json:"record"`
	Title      string          `gorm:"type:varchar(200)" json:"title"`
	Title2     string          `gorm:"type:varchar(200)" json:"title2"`
	Background string          `gorm:"type:text" json:"background"`
}

type UserVO struct {
	Nickname   string   `json:"nickname"`
	ImgUrl     string   `json:"imgUrl"`
	Github     string   `json:"github"`
	Email      string   `json:"email"`
	Record     string   `json:"record"`
	Title      string   `json:"title"`
	Title2     string   `json:"title2"`
	Background []string `json:"background"`
}

func FindUser(dto dto.LoginDTO) (user User) {
	model.Db.Table("t_user").Where("name = ?", dto.Username).First(&user)
	return user
}

func GetMessage() (userVo UserVO) {
	var user User
	model.Db.Table("t_user").First(&user)
	copier.Copy(&userVo, &user)
	_ = json.Unmarshal([]byte(user.Background), &userVo.Background)
	return userVo
}

func (User) TableName() string {
	return "t_user"
}

func ChangePic(picDTO dto.ChangeDTO, username any) {
	data, _ := json.Marshal(picDTO.Back)
	updates := map[string]interface{}{
		"img_url":    picDTO.Pic,
		"background": data,
	}
	model.Db.Table("t_user").Where("name = ?", username).Updates(updates)
}

func ChangeMessage(changeDTO dto.ChangeDTO, username any) {
	updates := map[string]interface{}{
		"email":  changeDTO.Email,
		"record": changeDTO.Record,
		"title":  changeDTO.Title,
		"title2": changeDTO.Title2,
		"github": changeDTO.Github,
	}
	model.Db.Table("t_user").Where("name = ?", username).Updates(updates)
}
