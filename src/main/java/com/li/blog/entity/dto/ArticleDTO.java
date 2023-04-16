package com.li.blog.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName ArticleDTO
 * @Description TODO
 * @Author Nine
 * @Date 2023/4/16 12:49
 * @Version 1.0
 */
@Data
public class ArticleDTO {
    @NotBlank( message = "标题不能为空")
    String title;
    @NotBlank( message = "概要不能为空")
    String descript;
    @NotBlank( message = "分类不能为空")
    String label;
    @NotBlank( message = "正文不能为空")
    String body;

    String id;
}
