package po

import (
	"blog/model"
	"blog/model/dto"
	"blog/utils"
)

type User struct {
	Id         int64           `gorm:"primaryKey;autoIncrement" json:"id"`
	Name       string          `json:"name"`
	Password   string          `json:"password"`
	Nickname   string          `json:"nickname"`
	GmtCreate  utils.LocalTime `gorm:"autoCreateTime:nano" json:"gmtCreate"`
	Imgurl     string          `json:"imgUrl"`
	Github     string          `json:"github"`
	Email      string          `json:"email"`
	Record     string          `json:"record"`
	Title      string          `json:"title"`
	Title2     string          `json:"title2"`
	Background string          `json:"background"`
}

func FindUser(dto dto.LoginDTO) (user User) {
	model.Db.Table("t_user").Where("name = ?", dto.Username).First(&user)
	return user
}
