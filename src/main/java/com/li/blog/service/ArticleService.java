package com.li.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.li.blog.bean.PageDTO;
import com.li.blog.bean.R;
import com.li.blog.entity.dto.QueryArticleDTO;
import com.li.blog.entity.po.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.li.blog.entity.vo.ArticleVO;
import com.li.blog.entity.vo.LabelVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nine
 * @since 2023-04-12
 */
public interface ArticleService extends IService<Article> {
    R<IPage<ArticleVO>> getArticlePage(QueryArticleDTO pageDTO);

    R<List<LabelVo>> getLabel();

    R<Article> getArticle(int id);
}
