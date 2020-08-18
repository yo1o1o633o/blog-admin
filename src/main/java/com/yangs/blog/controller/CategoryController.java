package com.yangs.blog.controller;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.wrapper.CategoryWrapper;
import com.yangs.blog.wrapper.TagWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CategoryController {
    @PostMapping("/category/list")
    public ResResult listCategory(@RequestBody @Valid CategoryWrapper.CategoryListDTO request) {
        return null;
    }

    @PostMapping("/category/add")
    public ResResult addCategory(@RequestBody @Valid CategoryWrapper.CategoryAddDTO request) {
        return null;
    }

    @PostMapping("/category/modify")
    public ResResult modifyCategory(@RequestBody @Valid CategoryWrapper.CategoryModifyDTO request) {
        return null;
    }

    @PostMapping("/category/remove")
    public ResResult removeCategory(@RequestBody @Valid CategoryWrapper.CategoryListDTO request) {
        return null;
    }
}
