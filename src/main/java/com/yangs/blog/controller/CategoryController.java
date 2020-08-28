package com.yangs.blog.controller;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.service.CategoryService;
import com.yangs.blog.wrapper.CategoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = {"http://localhost:8080"})
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/category/list")
    public ResResult listCategory(@RequestBody @Valid CategoryWrapper.CategoryListDTO request) {
        return categoryService.findAllCategoryList(request);
    }

    @PostMapping("/category/add")
    public ResResult addCategory(@RequestBody @Valid CategoryWrapper.CategoryAddDTO request) {
        categoryService.addCategory(request);
        return null;
    }

    @PostMapping("/category/modify")
    public ResResult modifyCategory(@RequestBody @Valid CategoryWrapper.CategoryModifyDTO request) {
        categoryService.modifyCategory(request);
        return null;
    }

    @PostMapping("/category/remove")
    public ResResult removeCategory(@RequestBody @Valid CategoryWrapper.CategoryListDTO request) {
        return null;
    }
}
