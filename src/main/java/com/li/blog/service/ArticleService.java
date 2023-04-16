package com.li.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.li.blog.bean.PageDTO;
import com.li.blog.bean.R;
import com.li.blog.entity.dto.ArticleDTO;
import com.li.blog.entity.dto.QueryArticleDTO;
import com.li.blog.entity.po.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.li.blog.entity.vo.ArticleTimeVo;
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
    /**
     * 文章列表
     * @param pageDTO
     * @return
     */
    R<IPage<ArticleVO>> getArticlePage(QueryArticleDTO pageDTO);

    /**
     * 获取标签
     * @return
     */
    R<List<LabelVo>> getLabel();

    /**
     * 根据ID获取文章详情
     * @param id
     * @return
     */
    R<Article> getArticle(String id);


    /**
     * 时间线文章列表
     */
    R<List<ArticleTimeVo>> getArticleTIme();

    /**
     * 新增文章
     * @param articleDTO
     * @return
     */
    R<String> addArticle(ArticleDTO articleDTO);

    /**
     * 更新文章
     * @param articleDTO
     * @return
     */
    public R<String> updateArticle(ArticleDTO articleDTO);

    public R<Article> deleteArticle(String id);
}
