package com.li.blog.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName ArticleVO
 * @Description TODO
 * @Author Nine
 * @Date 2023/4/12 16:24
 * @Version 1.0
 */
@Data
public class ArticleVO {
    private Integer id;

    private String title;

    private String descript;

    private LocalDateTime gmtCreate;

    private String label;
}
