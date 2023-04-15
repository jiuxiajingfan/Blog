package com.li.blog.controller;


import com.li.blog.bean.R;
import com.li.blog.bean.UnCheck;
import com.li.blog.entity.dto.ChangeMessageDTO;
import com.li.blog.entity.dto.ChangePicDTO;
import com.li.blog.entity.dto.ChangePwdDTO;
import com.li.blog.entity.dto.LoginDTO;
import com.li.blog.entity.vo.UserVo;
import com.li.blog.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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


    @PostMapping("/changePwd")
    @ApiOperation("修改密码")
    R<String> changePwd(@RequestBody @Validated ChangePwdDTO changePwdDto){
        return userService.changePwd(changePwdDto);
    }

    @PostMapping("/changePic")
    @ApiOperation("修改背景图")
    public R<String> changePic(@RequestBody @Validated ChangePicDTO changePicDTO){
        return userService.changePic(changePicDTO);
    }

    @PostMapping("/changeMessage")
    @ApiOperation("修改信息")
    public R<String> changeMessage(@RequestBody @Validated ChangeMessageDTO changeMessageDTO){
        return userService.changeMessage(changeMessageDTO);
    }
}

