package com.yangs.blog.service;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.entity.BlogTag;
import com.yangs.blog.wrapper.TagWrapper;

import java.util.List;

public interface TagService {
    ResResult findAllTagList(TagWrapper.TagListDTO request);

    List<BlogTag> findAllTag();

    void addTag(TagWrapper.TagAddDTO request);

    void modifyTag(TagWrapper.TagModifyDTO request);

    void modifyStatusTag(TagWrapper.TagModifyStatusDTO request);

    void removeTag(TagWrapper.TagDetailDTO request);
}
