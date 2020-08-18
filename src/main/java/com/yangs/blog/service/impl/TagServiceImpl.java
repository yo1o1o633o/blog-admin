package com.yangs.blog.service.impl;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.service.TagService;
import com.yangs.blog.wrapper.TagWrapper;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Override
    public ResResult findAllTagList(TagWrapper.TagListDTO request) {
        return null;
    }

    @Override
    public void addTag(TagWrapper.TagAddDTO request) {

    }

    @Override
    public void modifyTag(TagWrapper.TagModifyDTO request) {

    }

    @Override
    public void removeTag(TagWrapper.TagListDTO request) {

    }
}
