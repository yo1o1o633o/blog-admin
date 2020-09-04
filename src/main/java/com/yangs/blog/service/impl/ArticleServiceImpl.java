package com.yangs.blog.service.impl;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.entity.BlogArticle;
import com.yangs.blog.entity.BlogArticleTag;
import com.yangs.blog.entity.BlogCategory;
import com.yangs.blog.repository.BlogArticleRepository;
import com.yangs.blog.repository.BlogArticleTagRepository;
import com.yangs.blog.repository.BlogCategoryRepository;
import com.yangs.blog.service.ArticleService;
import com.yangs.blog.utils.TimeUtils;
import com.yangs.blog.vo.ArticleListVO;
import com.yangs.blog.wrapper.ArticleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    BlogArticleRepository blogArticleRepository;
    @Autowired
    BlogArticleTagRepository blogArticleTagRepository;
    @Autowired
    BlogCategoryRepository blogCategoryRepository;

    @Override
    public ResResult findAllArticle(ArticleWrapper.ArticleListDTO request) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
        Sort sort = Sort.by(orders);

        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<BlogArticle> resultPage = blogArticleRepository.findAll(pageRequest);
        List<BlogArticle> allArticle = resultPage.getContent();

        List<ArticleListVO> articleList = new ArrayList<>();
        for (BlogArticle article : allArticle) {
            ArticleListVO articleListVO = new ArticleListVO();
            articleListVO.setContent(article.getContent());
            articleListVO.setId(article.getId());
            articleListVO.setTitle(article.getTitle());
            articleListVO.setStatus(article.getStatus());
            articleListVO.setViewNum(article.getViewNum());
            articleListVO.setCreateTime(TimeUtils.formatTime(article.getCreateTime()));
            articleListVO.setUpdateTime(TimeUtils.formatTime(article.getUpdateTime()));
            if (article.getCategoryId() != null) {
                Optional<BlogCategory> categoryOptional = blogCategoryRepository.findById(article.getCategoryId());
                if (categoryOptional.isPresent()) {
                    BlogCategory category = categoryOptional.get();
                    articleListVO.setCategoryName(category.getName());
                }
            }
            articleList.add(articleListVO);
        }

        long count = blogArticleRepository.count();

        ResResult<ArticleListVO> resResult = new ResResult<>();
        resResult.setCount((int) count);
        resResult.setRow(articleList);
        return resResult;
    }

    @Override
    public void addArticle(ArticleWrapper.ArticleAddDTO request) {
        BlogArticle article = new BlogArticle();
        article.setTitle(request.getTitle());
        article.setDescription(request.getDescription());
        article.setContent(request.getContent());
        article.setCategoryId(request.getCategoryId());
        article.setArchiveTime(request.getTime());
        article.setStatus(1);
        article.setViewNum(0);
        article.setCreateTime((int) (System.currentTimeMillis() / 1000));
        article.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        blogArticleRepository.save(article);

        Integer id = article.getId();
        for (Integer tagId : request.getTagId()) {
            BlogArticleTag blogArticleTag = new BlogArticleTag();
            blogArticleTag.setArticleId(id);
            blogArticleTag.setTagId(tagId);

            blogArticleTagRepository.save(blogArticleTag);
        }
    }

    @Override
    public void modifyArticle(ArticleWrapper.ArticleModifyDTO request) {
        BlogArticle article = blogArticleRepository.findById(request.getId()).orElse(null);
        if (article != null) {
            article.setTitle(request.getTitle());
            article.setContent(request.getContent());
            article.setUpdateTime((int) (System.currentTimeMillis() / 1000));
            blogArticleRepository.save(article);
        }
    }

    @Override
    public void modifyStatusArticle(ArticleWrapper.ArticleModifyStatusDTO request) {
        BlogArticle article = blogArticleRepository.findById(request.getId()).orElse(null);
        if (article != null) {
            article.setStatus(request.getStatus());
            blogArticleRepository.save(article);
        }
    }

    @Override
    public BlogArticle findArticleById(ArticleWrapper.ArticleDetailDTO request) {
        return blogArticleRepository.findById(request.getId()).orElse(null);
    }

    @Override
    public void removeArticle(ArticleWrapper.ArticleDetailDTO request) {
        blogArticleRepository.deleteById(request.getId());
    }
}
