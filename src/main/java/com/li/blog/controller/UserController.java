package com.li.blog.controller;


import com.li.blog.bean.R;
import com.li.blog.bean.UnCheck;
import com.li.blog.entity.vo.UserVo;
import com.li.blog.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/getMessage")
    public R<UserVo> getMessage() {
        return userService.getMessage();
    }


}

