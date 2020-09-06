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
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    BlogTagRepository blogTagRepository;

    @Override
    public ResResult findAllTagList(TagWrapper.TagListDTO request) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
        Sort sort = Sort.by(orders);

        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<BlogTag> tagPage = blogTagRepository.findAll(pageRequest);

        List<TagListVO> tagList = new ArrayList<>();

        for (BlogTag tag : tagPage.getContent()) {
            TagListVO tagListVO = new TagListVO();
            tagListVO.setCreateTime(TimeUtils.formatTime(tag.getCreateTime()));
            tagListVO.setName(tag.getName());
            tagListVO.setStatus(tag.getStatus());
            tagListVO.setId(tag.getId());
            tagList.add(tagListVO);
        }

        long count = blogTagRepository.count();

        ResResult<TagListVO> result = new ResResult<>();
        result.setRow(tagList);
        result.setCount((int) count);
        return result;
    }

    @Override
    public List<BlogTag> findAllTag() {
        return blogTagRepository.findAllByStatus(1);
    }

    @Override
    public void addTag(TagWrapper.TagAddDTO request) {
        BlogTag blogTag = new BlogTag();
        blogTag.setName(request.getName());
        blogTag.setStatus(1);
        blogTag.setCreateTime((int) (System.currentTimeMillis() / 1000));
        blogTag.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        blogTagRepository.save(blogTag);
    }

    @Override
    public void modifyTag(TagWrapper.TagModifyDTO request) {
        BlogTag blogTag = blogTagRepository.findById(request.getId()).orElse(null);
        if (blogTag == null) {
            return;
        }
        blogTag.setName(request.getName());
        blogTagRepository.save(blogTag);
    }

    @Override
    public void modifyStatusTag(TagWrapper.TagModifyStatusDTO request) {
        BlogTag blogTag = blogTagRepository.findById(request.getId()).orElse(null);
        if (blogTag != null) {
            blogTag.setStatus(request.getStatus());
            blogTagRepository.save(blogTag);
        }
    }

    @Override
    public void removeTag(TagWrapper.TagDetailDTO request) {
        blogTagRepository.deleteById(request.getId());
    }
}
