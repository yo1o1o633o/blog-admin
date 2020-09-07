package com.yangs.blog.controller;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.entity.BlogTag;
import com.yangs.blog.service.TagService;
import com.yangs.blog.wrapper.TagWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping("/tag")
    public List<BlogTag> findAllTagList() {
        return tagService.findAllTag();
    }

    @PostMapping("/tag/list")
    public ResResult listTag(@RequestBody @Valid TagWrapper.TagListDTO request) {
        return tagService.findAllTagList(request);
    }

    @PostMapping("/tag/add")
    public String addTag(@RequestBody @Valid TagWrapper.TagAddDTO request) {
        tagService.addTag(request);
        return null;
    }

    @PostMapping("/tag/modify")
    public String modifyTag(@RequestBody @Valid TagWrapper.TagModifyDTO request) {
        tagService.modifyTag(request);
        return null;
    }

    @PostMapping("/tag/modify/status")
    public String modifyStatusTag(@RequestBody @Valid TagWrapper.TagModifyStatusDTO request) {
        tagService.modifyStatusTag(request);
        return null;
    }

    @PostMapping("/tag/remove")
    public String removeTag(@RequestBody @Valid TagWrapper.TagDetailDTO request) {
        tagService.removeTag(request);
        return null;
    }
}
