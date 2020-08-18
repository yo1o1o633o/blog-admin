package com.yangs.blog.controller;

import com.yangs.blog.wrapper.ArticleWrapper;
import com.yangs.blog.wrapper.TagWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TagController {
    @PostMapping("/tag/list")
    public String listTag(@RequestBody @Valid TagWrapper.TagListDTO request) {
        return null;
    }

    @PostMapping("/tag/add")
    public String addTag(@RequestBody @Valid TagWrapper.TagAddDTO request) {
        return null;
    }

    @PostMapping("/tag/modify")
    public String modifyTag(@RequestBody @Valid TagWrapper.TagModifyDTO request) {
        return null;
    }

    @PostMapping("/tag/remove")
    public String removeTag(@RequestBody @Valid TagWrapper.TagListDTO request) {
        return null;
    }
}
