package com.yangs.blog.service.impl;

import com.yangs.blog.common.PageResult;
import com.yangs.blog.entity.BlogTag;
import com.yangs.blog.repository.BlogTagRepository;
import com.yangs.blog.service.TagService;
import com.yangs.blog.utils.DozerUtils;
import com.yangs.blog.utils.SortUtils;
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
    public PageResult<TagListVO> findAllTagList(TagWrapper.TagListDTO request) {
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), SortUtils.sort(Sort.Direction.DESC, "id"));
        Page<BlogTag> tagPage = tagRepository.findAll(pageRequest);

        List<TagListVO> tagList = new ArrayList<>();

        for (BlogTag tag : tagPage.getContent()) {
            TagListVO tagListVO = DozerUtils.map(tag, TagListVO.class);
            tagListVO.setCreateTime(TimeUtils.formatTime(tag.getCreateTime()));
            tagList.add(tagListVO);
        }

        long count = tagRepository.count();

        PageResult<TagListVO> result = new PageResult<>();
        result.setRows(tagList);
        result.setTotal(count);
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
