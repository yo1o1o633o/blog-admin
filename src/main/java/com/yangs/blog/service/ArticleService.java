package com.yangs.blog.service;

import com.yangs.blog.common.PageResult;
import com.yangs.blog.vo.ArchiveListVO;
import com.yangs.blog.vo.ArticleDetailVO;
import com.yangs.blog.wrapper.ArticleWrapper;

import java.util.List;

/**
 * @author shuai.yang
 */
public interface ArticleService {
    PageResult findAllArticle(Integer page, Integer size);

    void addArticle(ArticleWrapper.ArticleAddDTO request);

    void modifyArticle(ArticleWrapper.ArticleModifyDTO request);

    void modifyStatusArticle(ArticleWrapper.ArticleModifyStatusDTO request);

    ArticleDetailVO findArticleById(Integer id);

    void removeArticle(Integer id);

    List<ArchiveListVO> findAllArchiveList();
}
