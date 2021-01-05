package com.yangs.blog.service;

import com.yangs.blog.common.PageResult;
import com.yangs.blog.entity.BlogTag;
import com.yangs.blog.vo.ArticleTagListVO;
import com.yangs.blog.vo.TagListVO;
import com.yangs.blog.wrapper.TagWrapper;

import java.util.List;

public interface TagService {
    PageResult<TagListVO> queryList(TagWrapper.TagListDTO request);

    List<BlogTag> findAllTag();

    void add(TagWrapper.TagAddDTO request);

    void modify(TagWrapper.TagModifyDTO request);

    void modify(TagWrapper.TagModifyStatusDTO request);

    void remove(TagWrapper.TagDetailDTO request);

    List<ArticleTagListVO> list(Integer articleId);
}
