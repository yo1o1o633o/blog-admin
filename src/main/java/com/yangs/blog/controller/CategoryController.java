package com.yangs.blog.controller;

import com.yangs.blog.common.BaseResult;
import com.yangs.blog.common.ResResult;
import com.yangs.blog.entity.BlogCategory;
import com.yangs.blog.service.CategoryService;
import com.yangs.blog.utils.ResUtils;
import com.yangs.blog.wrapper.CategoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/category")
    public List<BlogCategory> findAllCategory() {
        return categoryService.findAllCategoryList();
    }

    @PostMapping("/category/list")
    public ResResult listCategory(@RequestBody @Valid CategoryWrapper.CategoryListDTO request) {
        return categoryService.findAllCategory(request);
    }

    @PostMapping("/category/add")
    public BaseResult addCategory(@RequestBody @Valid CategoryWrapper.CategoryAddDTO request) {
        categoryService.addCategory(request);
        return ResUtils.suc();
    }

    @PostMapping("/category/modify")
    public BaseResult modifyCategory(@RequestBody @Valid CategoryWrapper.CategoryModifyDTO request) {
        categoryService.modifyCategory(request);
        return ResUtils.suc();
    }

    @PostMapping("/category/modify/status")
    public BaseResult modifyStatusCategory(@RequestBody @Valid CategoryWrapper.CategoryModifyStatusDTO request) {
        categoryService.modifyStatusCategory(request);
        return ResUtils.suc();
    }

    @PostMapping("/category/remove")
    public BaseResult removeCategory(@RequestBody @Valid CategoryWrapper.CategoryDetailDTO request) {
        categoryService.removeCategory(request);
        return ResUtils.suc();
    }
}
