package com.li.blog.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ChangePicDTO
 * @Description TODO
 * @Author Nine
 * @Date 2023/4/15 19:17
 * @Version 1.0
 */
@Data
public class ChangePicDTO {
    List<String> back;
    String pic;
}
