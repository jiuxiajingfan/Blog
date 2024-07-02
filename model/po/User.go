package po

import (
	"blog/model"
	"blog/model/dto"
	"blog/utils"
)

type User struct {
	Id         int64           `gorm:"primaryKey" json:"id"`
	Name       string          `gorm:"type:varchar(20)" json:"name"`
	Password   string          `gorm:"type:varchar(100)" json:"password"`
	Nickname   string          `gorm:"type:varchar(20)" json:"nickname"`
	GmtCreate  utils.LocalTime `gorm:"autoCreateTime:nano" json:"gmtCreate"`
	Imgurl     string          `gorm:"type:varchar(500)" json:"imgUrl"`
	Github     string          `gorm:"type:varchar(500)" json:"github"`
	Email      string          `gorm:"type:varchar(500)" json:"email"`
	Record     string          `gorm:"type:varchar(100)" json:"record"`
	Title      string          `gorm:"type:varchar(200)" json:"title"`
	Title2     string          `gorm:"type:varchar(200)" json:"title2"`
	Background string          `gorm:"type:text" json:"background"`
}

func FindUser(dto dto.LoginDTO) (user User) {
	model.Db.Table("t_user").Where("name = ?", dto.Username).First(&user)
	return user
}

func (User) TableName() string {
	return "t_user"
}
