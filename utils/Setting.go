package utils

import (
	"fmt"
	"gopkg.in/ini.v1"
)

var (
	HttpPort string

	DbHost        string
	DbPort        string
	DbUser        string
	DbPassWord    string
	DbName        string
	RedisHost     string
	RedisPort     string
	RedisPassWord string
	RedisDatabase int

	JwtSecret string
)

// 初始化
func init() {
	file, err := ini.Load("config/config.ini")
	if err != nil {
		fmt.Println("配置文件读取错误，请检查文件路径:", err)
	}
	LoadServer(file)
	LoadData(file)
}

func LoadServer(file *ini.File) {
	HttpPort = file.Section("server").Key("HttpPort").MustString(":3000")
}

func LoadData(file *ini.File) {
	DbHost = file.Section("database").Key("DbHost").MustString("localhost")
	DbPort = file.Section("database").Key("DbPort").MustString("3306")
	DbUser = file.Section("database").Key("DbUser").MustString("ginblog")
	DbPassWord = file.Section("database").Key("DbPassWord").String()
	DbName = file.Section("database").Key("DbName").MustString("ginblog")
	RedisDatabase = file.Section("redis").Key("RedisDatabase").MustInt(0)
	RedisPassWord = file.Section("redis").Key("RedisPassWord").MustString("")
	RedisHost = file.Section("redis").Key("RedisHost").MustString("localhost")
	RedisPort = file.Section("redis").Key("RedisPort").MustString("6379")
	JwtSecret = file.Section("jwt").Key("JwtSecret").MustString("123456")
}