package com.li.blog.entity.dto;

import com.li.blog.bean.PageDTO;
import lombok.Data;

/**
 * @ClassName QueryArticleDTO
 * @Description TODO
 * @Author Nine
 * @Date 2023/4/12 18:35
 * @Version 1.0
 */
@Data
public class QueryArticleDTO extends PageDTO {
    String label;
    String title;
}
