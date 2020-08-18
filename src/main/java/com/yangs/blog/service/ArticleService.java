package com.yangs.blog.service;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.wrapper.ArticleWrapper;


public interface ArticleService {
    ResResult findAllArticle(ArticleWrapper.ArticleListDTO request);

    void addArticle(ArticleWrapper.ArticleAddDTO request);

    void modifyArticle(ArticleWrapper.ArticleModifyDTO request);
}
