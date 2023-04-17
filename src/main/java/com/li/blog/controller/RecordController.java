package com.li.blog.controller;


import com.li.blog.bean.R;
import com.li.blog.bean.UnCheck;
import com.li.blog.service.RecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/record")
public class RecordController {
    @Resource
    private RecordService recordService;

    @GetMapping("/getGuest")
    @UnCheck
    @ApiOperation(value = "获取浏览量")
    public R<String> getGuest(){
        return recordService.getGuest();
    }
}

