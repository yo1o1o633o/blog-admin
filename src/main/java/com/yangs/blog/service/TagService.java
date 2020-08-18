package com.yangs.blog.service;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.wrapper.TagWrapper;

public interface TagService {
    ResResult findAllTagList(TagWrapper.TagListDTO request);

    void addTag(TagWrapper.TagAddDTO request);

    void modifyTag(TagWrapper.TagModifyDTO request);

    void removeTag(TagWrapper.TagListDTO request);
}
