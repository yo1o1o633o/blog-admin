package com.yangs.blog.service;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.vo.ArchiveListVO;
import com.yangs.blog.vo.ArticleDetailVO;
import com.yangs.blog.wrapper.ArticleWrapper;

import java.util.List;


public interface ArticleService {
    ResResult findAllArticle(ArticleWrapper.ArticleListDTO request);

    void addArticle(ArticleWrapper.ArticleAddDTO request);

    void modifyArticle(ArticleWrapper.ArticleModifyDTO request);

    void modifyStatusArticle(ArticleWrapper.ArticleModifyStatusDTO request);

    ArticleDetailVO findArticleById(ArticleWrapper.ArticleDetailDTO request);

    void removeArticle(ArticleWrapper.ArticleDetailDTO request);

    List<ArchiveListVO> findAllArchiveList();
}
