package com.li.blog.controller;


import com.li.blog.bean.R;
import com.li.blog.bean.UnCheck;
import com.li.blog.entity.dto.LoginDTO;
import com.li.blog.entity.vo.UserVo;
import com.li.blog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nine
 * @since 2023-04-12
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @UnCheck
    @ApiOperation("获取前台信息")
    @GetMapping("/getMessage")
    public R<UserVo> getMessage() {
        return userService.getMessage();
    }

    @PostMapping("/login")
    @UnCheck
    @ApiOperation("登录")
    public R<String> login(@RequestBody @Validated LoginDTO loginDTO){
        return userService.login(loginDTO);
    }
}

