package com.yangs.blog.service;

import com.yangs.blog.vo.ArticleListVO;
import com.yangs.blog.wrapper.ArticleWrapper;

import java.util.List;

public interface ArticleService {
    List<ArticleListVO> findAllArticle(ArticleWrapper.ArticleListDTO request);

    void addArticle(ArticleWrapper.ArticleAddDTO request);

    void modifyArticle(ArticleWrapper.ArticleModifyDTO request);
}
