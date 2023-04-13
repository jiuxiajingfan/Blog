package com.li.blog.mapper;

import com.li.blog.entity.po.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.blog.entity.vo.ArticleVO;
import com.li.blog.entity.vo.LabelVo;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
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

    @Select("select distinct year(gmt_create) as year from t_article order by year desc")
    List<Integer> getTimeList();

    List<ArticleVO> getArticle();
}
