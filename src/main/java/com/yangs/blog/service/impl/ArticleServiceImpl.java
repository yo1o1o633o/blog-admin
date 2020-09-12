package com.yangs.blog.service.impl;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.entity.BlogArticle;
import com.yangs.blog.entity.BlogArticleTag;
import com.yangs.blog.entity.BlogCategory;
import com.yangs.blog.entity.BlogTag;
import com.yangs.blog.repository.BlogArticleRepository;
import com.yangs.blog.repository.BlogArticleTagRepository;
import com.yangs.blog.repository.BlogCategoryRepository;
import com.yangs.blog.repository.BlogTagRepository;
import com.yangs.blog.service.ArticleService;
import com.yangs.blog.utils.TimeUtils;
import com.yangs.blog.vo.*;
import com.yangs.blog.wrapper.ArticleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    BlogArticleRepository articleRepository;
    @Autowired
    BlogArticleTagRepository articleTagRepository;
    @Autowired
    BlogCategoryRepository categoryRepository;
    @Autowired
    BlogTagRepository tagRepository;

    @Override
    public ResResult findAllArticle(Integer page, Integer size) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
        Sort sort = Sort.by(orders);

        PageRequest pageRequest = PageRequest.of(page - 1, size, sort);
        Page<BlogArticle> resultPage = articleRepository.findAll(pageRequest);
        List<BlogArticle> allArticle = resultPage.getContent();

        List<ArticleListVO> articleList = new ArrayList<>();
        for (BlogArticle article : allArticle) {
            ArticleListVO articleListVO = new ArticleListVO();
            articleListVO.setContent(article.getContent());
            articleListVO.setId(article.getId());
            articleListVO.setDescription(article.getDescription());
            articleListVO.setTitle(article.getTitle());
            articleListVO.setStatus(article.getStatus());
            articleListVO.setViewNum(article.getViewNum());
            articleListVO.setCreateTime(TimeUtils.formatTime(article.getCreateTime()));
            articleListVO.setUpdateTime(TimeUtils.formatTime(article.getUpdateTime()));
            articleListVO.setArchiveTime(TimeUtils.formatTime(article.getArchiveTime()));
            if (article.getCategoryId() != null) {
                categoryRepository.findById(article.getCategoryId()).ifPresent(category -> articleListVO.setCategoryName(category.getName()));
            }
            List<BlogArticleTag> articleTags = articleTagRepository.findAllByArticleId(article.getId());
            List<ArticleTagListVO> articleTagListVOList = new ArrayList<>();
            for (BlogArticleTag articleTag : articleTags) {
                BlogTag blogTag = tagRepository.findById(articleTag.getTagId()).orElse(null);
                if (blogTag != null) {
                    ArticleTagListVO articleTagListVO = new ArticleTagListVO();
                    articleTagListVO.setId(blogTag.getId());
                    articleTagListVO.setName(blogTag.getName());
                    articleTagListVOList.add(articleTagListVO);
                }
            }
            articleListVO.setTagList(articleTagListVOList);
            articleList.add(articleListVO);
        }

        long count = articleRepository.count();

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
        article.setCreateTime(TimeUtils.getCurrentTime());
        article.setUpdateTime(TimeUtils.getCurrentTime());
        articleRepository.save(article);

        Integer id = article.getId();
        for (Integer tagId : request.getTagId()) {
            BlogArticleTag blogArticleTag = new BlogArticleTag();
            blogArticleTag.setArticleId(id);
            blogArticleTag.setTagId(tagId);

            articleTagRepository.save(blogArticleTag);
        }
    }

    @Override
    @Transactional
    public void modifyArticle(ArticleWrapper.ArticleModifyDTO request) {
        BlogArticle article = articleRepository.findById(request.getId()).orElse(null);
        if (article == null) {
            return;
        }
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setDescription(request.getDescription());
        article.setArchiveTime(request.getTime());
        article.setCategoryId(request.getCategoryId());
        articleTagRepository.deleteAllByArticleId(article.getId());
        if (request.getTagIds() != null && request.getTagIds().size() > 0) {
            for (Integer tagId : request.getTagIds()) {
                BlogArticleTag blogArticleTag = new BlogArticleTag();
                blogArticleTag.setArticleId(article.getId());
                blogArticleTag.setTagId(tagId);

                articleTagRepository.save(blogArticleTag);
            }
        }
        article.setUpdateTime(TimeUtils.getCurrentTime());
        articleRepository.save(article);
    }

    @Override
    public void modifyStatusArticle(ArticleWrapper.ArticleModifyStatusDTO request) {
        BlogArticle article = articleRepository.findById(request.getId()).orElse(null);
        if (article != null) {
            article.setStatus(request.getStatus());
            articleRepository.save(article);
        }
    }

    @Override
    public ArticleDetailVO findArticleById(Integer id) {
        BlogArticle article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return null;
        }
        ArticleDetailVO articleDetailVO = new ArticleDetailVO();
        articleDetailVO.setId(article.getId());
        articleDetailVO.setTitle(article.getTitle());
        articleDetailVO.setContent(article.getContent());
        articleDetailVO.setDescription(article.getDescription());
        articleDetailVO.setCategoryId(article.getCategoryId());

        List<BlogArticleTag> articleTagList = articleTagRepository.findAllByArticleId(article.getId());
        List<Integer> tagList = new ArrayList<>();
        List<String> tagNameList = new ArrayList<>();
        for (BlogArticleTag articleTag : articleTagList) {
            tagList.add(articleTag.getTagId());
            if (articleTag.getTagId() != null) {
                BlogTag blogTag = tagRepository.findById(articleTag.getTagId()).orElse(null);
                if (blogTag == null) {
                    continue;
                }
                tagNameList.add(blogTag.getName());
            }
        }
        articleDetailVO.setTagList(tagList);
        articleDetailVO.setArchiveTime(article.getArchiveTime());
        articleDetailVO.setArchiveTimeStr(TimeUtils.formatTime(article.getArchiveTime()));
        articleDetailVO.setTagNameList(tagNameList);
        return articleDetailVO;
    }

    @Override
    public void removeArticle(Integer id) {
        articleRepository.findById(id).ifPresent(article -> articleRepository.deleteById(id));
    }

    @Override
    public List<ArchiveListVO> findAllArchiveList() {
        List<ArchiveListVO> archiveListVOList = new ArrayList<>();

        ArchiveListVO archiveListVO = new ArchiveListVO();
        archiveListVO.setYear("2020年");

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "archiveTime"));
        Sort sort = Sort.by(orders);

        List<BlogArticle> articleList = articleRepository.findAll(sort);
        List<ArchiveItem> archiveItems = new ArrayList<>();
        for (BlogArticle article : articleList) {
            ArchiveItem archiveItem = new ArchiveItem();
            archiveItem.setId(article.getId());
            archiveItem.setTitle(article.getTitle());
            if (article.getArchiveTime() != null) {
                archiveItem.setDate(TimeUtils.formatTimeToDay(article.getArchiveTime()));
            }
            archiveItems.add(archiveItem);
        }

        archiveListVO.setArchiveItem(archiveItems);
        archiveListVOList.add(archiveListVO);
        return archiveListVOList;
    }
}
