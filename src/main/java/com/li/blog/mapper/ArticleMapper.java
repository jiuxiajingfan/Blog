package com.li.blog.mapper;

import com.li.blog.entity.po.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.blog.entity.vo.LabelVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nine
 * @since 2023-04-12
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<LabelVo> getLabel();
}
