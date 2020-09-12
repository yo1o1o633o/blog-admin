package com.yangs.blog.service.impl;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.entity.BlogTag;
import com.yangs.blog.repository.BlogTagRepository;
import com.yangs.blog.service.TagService;
import com.yangs.blog.utils.TimeUtils;
import com.yangs.blog.vo.TagListVO;
import com.yangs.blog.wrapper.TagWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    BlogTagRepository tagRepository;

    @Override
    public ResResult findAllTagList(TagWrapper.TagListDTO request) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
        Sort sort = Sort.by(orders);

        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<BlogTag> tagPage = tagRepository.findAll(pageRequest);

        List<TagListVO> tagList = new ArrayList<>();

        for (BlogTag tag : tagPage.getContent()) {
            TagListVO tagListVO = new TagListVO();
            tagListVO.setCreateTime(TimeUtils.formatTime(tag.getCreateTime()));
            tagListVO.setName(tag.getName());
            tagListVO.setStatus(tag.getStatus());
            tagListVO.setId(tag.getId());
            tagList.add(tagListVO);
        }

        long count = tagRepository.count();

        ResResult<TagListVO> result = new ResResult<>();
        result.setRow(tagList);
        result.setCount((int) count);
        return result;
    }

    @Override
    public List<BlogTag> findAllTag() {
        return tagRepository.findAllByStatus(1);
    }

    @Override
    public void addTag(TagWrapper.TagAddDTO request) {
        BlogTag blogTag = new BlogTag();
        blogTag.setName(request.getName());
        blogTag.setStatus(1);
        blogTag.setCreateTime(TimeUtils.getCurrentTime());
        blogTag.setUpdateTime(TimeUtils.getCurrentTime());
        tagRepository.save(blogTag);
    }

    @Override
    public void modifyTag(TagWrapper.TagModifyDTO request) {
        tagRepository.findById(request.getId()).ifPresent(tag -> {
            tag.setName(request.getName());
            tagRepository.save(tag);
        });
    }

    @Override
    public void modifyStatusTag(TagWrapper.TagModifyStatusDTO request) {
        BlogTag blogTag = tagRepository.findById(request.getId()).orElse(null);
        if (blogTag != null) {
            blogTag.setStatus(request.getStatus());
            tagRepository.save(blogTag);
        }
    }

    @Override
    public void removeTag(TagWrapper.TagDetailDTO request) {
        tagRepository.findById(request.getId()).ifPresent(tag -> tagRepository.deleteById(request.getId()));
    }
}
