package com.yangs.blog.controller;

import com.yangs.blog.common.PageResult;
import utils.ResResult;
import com.yangs.blog.entity.BlogCategory;
import com.yangs.blog.service.CategoryService;
import com.yangs.blog.utils.ResUtils;
import com.yangs.blog.vo.CategoryListVO;
import com.yangs.blog.wrapper.CategoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author shuai.yang
 */
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/category")
    public ResResult<List<BlogCategory>> findAllCategory() {
        return ResUtils.data(categoryService.findAllCategoryList());
    }

    @PostMapping("/category/list")
    public ResResult<PageResult<CategoryListVO>> listCategory(@RequestBody @Valid CategoryWrapper.CategoryListDTO request) {
        return ResUtils.data(categoryService.findAllCategory(request));
    }

    @PostMapping("/category/add")
    public ResResult addCategory(@RequestBody @Valid CategoryWrapper.CategoryAddDTO request) {
        categoryService.addCategory(request);
        return ResUtils.suc();
    }

    @PostMapping("/category/modify")
    public ResResult modifyCategory(@RequestBody @Valid CategoryWrapper.CategoryModifyDTO request) {
        categoryService.modifyCategory(request);
        return ResUtils.suc();
    }

    @PostMapping("/category/modify/status")
    public ResResult modifyStatusCategory(@RequestBody @Valid CategoryWrapper.CategoryModifyStatusDTO request) {
        categoryService.modifyStatusCategory(request);
        return ResUtils.suc();
    }

    @PostMapping("/category/remove")
    public ResResult removeCategory(@RequestBody @Valid CategoryWrapper.CategoryDetailDTO request) {
        categoryService.removeCategory(request);
        return ResUtils.suc();
    }
}
