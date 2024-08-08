package dto

type LoginDTO struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

type ChangeDTO struct {
	Pic    string   `json:"pic"`
	Back   []string `json:"back"`
	Email  string   `json:"email"`
	Github string   `json:"github"`
	Record string   `json:"record"`
	Title  string   `json:"title"`
	Title2 string   `json:"title2"`
}
