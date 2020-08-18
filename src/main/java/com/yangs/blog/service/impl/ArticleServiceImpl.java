package com.yangs.blog.service.impl;

import com.yangs.blog.entity.BlogArticle;
import com.yangs.blog.repository.BlogArticleRepository;
import com.yangs.blog.service.ArticleService;
import com.yangs.blog.utils.TimeUtils;
import com.yangs.blog.vo.ArticleListVO;
import com.yangs.blog.wrapper.ArticleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    BlogArticleRepository blogArticleRepository;

    @Override
    public List<ArticleListVO> findAllArticle(ArticleWrapper.ArticleListDTO request) {
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<BlogArticle> resultPage = blogArticleRepository.findAll(pageRequest);
        List<BlogArticle> allArticle = resultPage.getContent();

        List<ArticleListVO> articleList = new ArrayList<>();
        for (BlogArticle article : allArticle) {
            ArticleListVO articleListVO = new ArticleListVO();
            articleListVO.setContent(article.getContent());
            articleListVO.setId(article.getId());
            articleListVO.setTitle(article.getTitle());
            articleListVO.setStatus(article.getStatus() == 1 ? "显示" : "隐藏");
            articleListVO.setViewNum(article.getViewNum());
            articleListVO.setCreateTime(TimeUtils.formatTime(article.getCreateTime()));
            articleListVO.setUpdateTime(TimeUtils.formatTime(article.getUpdateTime()));
            articleList.add(articleListVO);
        }
        return articleList;
    }

    @Override
    public void addArticle(ArticleWrapper.ArticleAddDTO request) {
        BlogArticle article = new BlogArticle();
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setStatus(1);
        article.setViewNum(0);
        article.setCreateTime((int) (System.currentTimeMillis() / 1000));
        article.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        blogArticleRepository.save(article);
    }

    @Override
    public void modifyArticle(ArticleWrapper.ArticleModifyDTO request) {

    }
}
