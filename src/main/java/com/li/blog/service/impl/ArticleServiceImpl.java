package com.li.blog.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.blog.bean.R;
import com.li.blog.entity.dto.QueryArticleDTO;
import com.li.blog.entity.po.Article;
import com.li.blog.entity.vo.ArticleVO;
import com.li.blog.entity.vo.LabelVo;
import com.li.blog.mapper.ArticleMapper;
import com.li.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nine
 * @since 2023-04-12
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private  ArticleMapper articleMapper;

    @Override
    public R<IPage<ArticleVO>> getArticlePage(QueryArticleDTO pageDTO) {
        LambdaQueryWrapper<Article> wrapper = Wrappers.lambdaQuery(Article.class)
                .eq(!StringUtils.isBlank(pageDTO.getLabel()),Article::getLabel,pageDTO.getLabel())
                .like(!StringUtils.isBlank(pageDTO.getTitle()),Article::getTitle,pageDTO.getLabel())
                .orderByDesc(Article::getGmtCreate);
        Page<Article> page = new Page<>(pageDTO.getCurrent(), pageDTO.getPageSize());
        return R.ok(articleMapper.selectPage(page, wrapper).convert(e-> CglibUtil.copy(e, ArticleVO.class)));
    }

    @Override
    public R<List<LabelVo>> getLabel() {
        return R.ok(articleMapper.getLabel());
    }

    @Override
    public R<Article> getArticle(int id) {
        Article data = articleMapper.selectById(id);
        if(Objects.isNull(data)){
            return R.error("无此文章！");
        }
        return R.ok(data);
    }
}
