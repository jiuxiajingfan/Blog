package com.li.blog.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName LoginDTO
 * @Description TODO
 * @Author Nine
 * @Date 2023/4/14 16:40
 * @Version 1.0
 */
@Data
public class LoginDTO {
    @NotBlank( message = "账号不能为空！")
    String name;
    @NotBlank( message = "密码不能为空！")
    String password;
}
