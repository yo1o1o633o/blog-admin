package com.yangs.blog.service.impl;

import com.yangs.blog.common.PageResult;
import com.yangs.blog.entity.BlogArticle;
import com.yangs.blog.entity.BlogArticleTag;
import com.yangs.blog.entity.BlogTag;
import com.yangs.blog.repository.BlogArticleRepository;
import com.yangs.blog.repository.BlogArticleTagRepository;
import com.yangs.blog.repository.BlogCategoryRepository;
import com.yangs.blog.repository.BlogTagRepository;
import com.yangs.blog.service.ArticleService;
import com.yangs.blog.service.TagService;
import com.yangs.blog.utils.DozerUtils;
import com.yangs.blog.utils.SortUtils;
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

/**
 * @author shuai.yang
 */
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
    @Autowired
    TagService tagService;

    @Override
    public PageResult<ArticleListVO> list(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, SortUtils.sort(Sort.Direction.DESC, "id"));
        Page<BlogArticle> resultPage = articleRepository.findAll(pageRequest);

        PageResult<ArticleListVO> result = new PageResult<>();
        result.setTotal(articleRepository.count());
        result.setRows(constructList(resultPage.getContent()));
        return result;
    }

    private List<ArticleListVO> constructList(List<BlogArticle> allArticle) {
        List<ArticleListVO> articleList = new ArrayList<>();
        for (BlogArticle article : allArticle) {
            articleList.add(constructResult(article));
        }
        return articleList;
    }

    private ArticleListVO constructResult(BlogArticle article) {
        ArticleListVO articleListVO = DozerUtils.map(article, ArticleListVO.class);
        articleListVO.setCreateTime(TimeUtils.formatTime(article.getCreateTime()));
        articleListVO.setUpdateTime(TimeUtils.formatTime(article.getUpdateTime()));
        articleListVO.setArchiveTime(TimeUtils.formatTime(article.getArchiveTime()));
        articleListVO.setTagList(tagService.list(article.getId()));
        categoryRepository.findById(article.getCategoryId()).ifPresent(category -> articleListVO.setCategoryName(category.getName()));
        return articleListVO;
    }

    @Override
    public void add(wrapper.ArticleWrapper.AddDTO addDTO) {
        BlogArticle article = DozerUtils.map(addDTO, BlogArticle.class);
        article.setStatus(1);
        article.setViewNum(0);
        article.setCreateTime(TimeUtils.getCurrentTime());
        article.setUpdateTime(TimeUtils.getCurrentTime());
        articleRepository.save(article);

        Integer id = article.getId();
        for (Integer tagId : addDTO.getTagId()) {
            BlogArticleTag blogArticleTag = new BlogArticleTag();
            blogArticleTag.setArticleId(id);
            blogArticleTag.setTagId(tagId);

            articleTagRepository.save(blogArticleTag);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void modify(ArticleWrapper.ArticleModifyDTO request) {
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
    public void modify(ArticleWrapper.ArticleModifyStatusDTO request) {
        BlogArticle article = articleRepository.findById(request.getId()).orElse(null);
        if (article != null) {
            article.setStatus(request.getStatus());
            articleRepository.save(article);
        }
    }

    @Override
    public ArticleDetailVO detail(Integer id) {
        BlogArticle article = articleRepository.findById(id).orElse(null);
        if (article == null) {
            return null;
        }
        ArticleDetailVO articleDetailVO = DozerUtils.map(article, ArticleDetailVO.class);

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
        articleDetailVO.setArchiveTimeStr(TimeUtils.formatTime(article.getArchiveTime()));
        articleDetailVO.setTagNameList(tagNameList);
        return articleDetailVO;
    }

    @Override
    public void remove(Integer id) {
        articleRepository.findById(id).ifPresent(article -> articleRepository.deleteById(id));
    }

    @Override
    public List<ArchiveListVO> findAllArchiveList() {
        List<ArchiveListVO> archiveListVOList = new ArrayList<>();

        ArchiveListVO archiveListVO = new ArchiveListVO();
        archiveListVO.setYear("2020年");

        List<BlogArticle> articleList = articleRepository.findAll(SortUtils.sort(Sort.Direction.DESC, "archiveTime"));
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
