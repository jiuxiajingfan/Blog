package com.li.blog.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ArticleTimeVo
 * @Description TODO
 * @Author Nine
 * @Date 2023/4/13 23:43
 * @Version 1.0
 */
@Data
public class ArticleTimeVo {
    String time;
    List<ArticleVO> list;
}
