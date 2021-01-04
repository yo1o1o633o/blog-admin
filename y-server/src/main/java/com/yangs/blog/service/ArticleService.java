package com.yangs.blog.service;

import com.yangs.blog.common.PageResult;
import com.yangs.blog.vo.ArchiveListVO;
import com.yangs.blog.vo.ArticleDetailVO;
import com.yangs.blog.vo.ArticleListVO;
import com.yangs.blog.wrapper.ArticleWrapper;

import java.util.List;

/**
 * @author shuai.yang
 */
public interface ArticleService {
    PageResult<ArticleListVO> list(Integer page, Integer size);

    void add(ArticleWrapper.ArticleAddDTO request);

    void modify(ArticleWrapper.ArticleModifyDTO request);

    void modify(ArticleWrapper.ArticleModifyStatusDTO request);

    ArticleDetailVO detail(Integer id);

    void remove(Integer id);

    List<ArchiveListVO> findAllArchiveList();
}
