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
import org.dozer.loader.DozerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
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
    @Autowired
    BlogTagRepository blogTagRepository;

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
            articleListVO.setDescription(article.getDescription());
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
            List<BlogArticleTag> articleTags = blogArticleTagRepository.findAllByArticleId(article.getId());
            List<ArticleTagListVO> articleTagListVOList = new ArrayList<>();
            for (BlogArticleTag articleTag : articleTags) {
                BlogTag blogTag = blogTagRepository.findById(articleTag.getTagId()).orElse(null);
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
    @Transactional
    public void modifyArticle(ArticleWrapper.ArticleModifyDTO request) {
        BlogArticle article = blogArticleRepository.findById(request.getId()).orElse(null);
        if (article != null) {
            article.setTitle(request.getTitle());
            article.setContent(request.getContent());
            article.setDescription(request.getDescription());
            article.setArchiveTime(request.getTime());
            article.setCategoryId(request.getCategoryId());
            blogArticleTagRepository.deleteAllByArticleId(article.getId());
            if (request.getTagIds() != null && request.getTagIds().size() > 0) {
                for (Integer tagId : request.getTagIds()) {
                    BlogArticleTag blogArticleTag = new BlogArticleTag();
                    blogArticleTag.setArticleId(article.getId());
                    blogArticleTag.setTagId(tagId);

                    blogArticleTagRepository.save(blogArticleTag);
                }
            }
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
    public ArticleDetailVO findArticleById(ArticleWrapper.ArticleDetailDTO request) {
        BlogArticle article = blogArticleRepository.findById(request.getId()).orElse(null);
        if (article == null) {
            return null;
        }
        ArticleDetailVO articleDetailVO = new ArticleDetailVO();
        articleDetailVO.setId(article.getId());
        articleDetailVO.setTitle(article.getTitle());
        articleDetailVO.setContent(article.getContent());
        articleDetailVO.setDescription(article.getDescription());
        articleDetailVO.setCategoryId(article.getCategoryId());

        List<BlogArticleTag> articleTagList = blogArticleTagRepository.findAllByArticleId(article.getId());
        List<Integer> tagList = new ArrayList<>();
        List<String> tagNameList = new ArrayList<>();
        for (BlogArticleTag articleTag : articleTagList) {
            tagList.add(articleTag.getTagId());
            if (articleTag.getTagId() != null) {
                BlogTag blogTag = blogTagRepository.findById(articleTag.getTagId()).orElse(null);
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
    public void removeArticle(ArticleWrapper.ArticleDetailDTO request) {
        blogArticleRepository.deleteById(request.getId());
    }

    @Override
    public List<ArchiveListVO> findAllArchiveList() {
        List<ArchiveListVO> archiveListVOList = new ArrayList<>();

        ArchiveListVO archiveListVO = new ArchiveListVO();
        archiveListVO.setYear("2020年");

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "archiveTime"));
        Sort sort = Sort.by(orders);

        List<BlogArticle> articleList = blogArticleRepository.findAll(sort);
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
