package com.li.blog.service.impl;

import cn.hutool.extra.cglib.CglibUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.blog.bean.R;
import com.li.blog.entity.dto.ArticleDTO;
import com.li.blog.entity.dto.QueryArticleDTO;
import com.li.blog.entity.po.Article;
import com.li.blog.entity.vo.ArticleTimeVo;
import com.li.blog.entity.vo.ArticleVO;
import com.li.blog.entity.vo.LabelVo;
import com.li.blog.mapper.ArticleMapper;
import com.li.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
                .like(!StringUtils.isBlank(pageDTO.getTitle()),Article::getTitle,pageDTO.getTitle())
                .orderByDesc(Article::getGmtCreate);
        Page<Article> page = new Page<>(pageDTO.getCurrent(), pageDTO.getPageSize());
        return R.ok(articleMapper.selectPage(page, wrapper).convert(e-> CglibUtil.copy(e, ArticleVO.class)));
    }

    @Override
    public R<List<LabelVo>> getLabel() {
        List<LabelVo> ans = new ArrayList<>();
        LabelVo labelVo = new LabelVo();
        labelVo.setLabel("全部");
        List<LabelVo> label = articleMapper.getLabel();
        labelVo.setNum(label.stream().mapToInt(LabelVo::getNum).sum());
        ans.add(labelVo);
        ans.addAll(label);
        return R.ok(ans);
    }

    @Override
    public R<Article> getArticle(String id) {
        Article data = articleMapper.selectById(Long.parseLong(id));
        if(Objects.isNull(data)){
            return R.error("无此文章！");
        }
        return R.ok(data);
    }

    @Override
    public R<List<ArticleTimeVo>> getArticleTIme() {
        List<Integer> timeList = articleMapper.getTimeList();
        if(timeList.isEmpty())
            return R.error("不存在文章");
        List<ArticleVO> articleList = articleMapper.getArticle();
        List<ArticleTimeVo> ans = timeList.stream().map(e -> {
            ArticleTimeVo articleTimeVo = new ArticleTimeVo();
            articleTimeVo.setTime(Integer.toString(e));
            articleTimeVo.setList(
                    articleList.stream().filter(x -> {
                        return x.getGmtCreate().getYear() == e;
                    }).collect(Collectors.toList())
            );
            return articleTimeVo;
        }).collect(Collectors.toList());
        return R.ok(ans);
    }

    @Override
    public R<String> addArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setBody(articleDTO.getBody());
        article.setTitle(articleDTO.getTitle());
        article.setLabel(articleDTO.getLabel());
        article.setDescript(articleDTO.getDescript());
        int i = articleMapper.insert(article);
        if (i == 1) {
            return R.ok("新增成功");
        }else{
            return R.error("新增失败");
        }
    }
    @Override
    public R<String> updateArticle(ArticleDTO articleDTO) {
        Article article = articleMapper.selectById(Long.parseLong(articleDTO.getId()));
        if(Objects.isNull(article)){
            return R.error("无此文章！");
        }
        article.setBody(articleDTO.getBody());
        article.setTitle(articleDTO.getTitle());
        article.setLabel(articleDTO.getLabel());
        article.setDescript(articleDTO.getDescript());
        int i = articleMapper.updateById(article);
        if (i == 1) {
            return R.ok("更新成功");
        }else{
            return R.error("更新失败");
        }
    }

    @Override
    public R<Article> deleteArticle(String id) {
        int i = articleMapper.deleteById(Long.parseLong(id));
        if (i == 1) {
            return R.ok("删除成功");
        }else{
            return R.error("删除失败");
        }
    }
}
