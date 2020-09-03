package com.yangs.blog.service;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.entity.BlogCategory;
import com.yangs.blog.wrapper.CategoryWrapper;

import java.util.List;

public interface CategoryService {
    ResResult findAllCategory(CategoryWrapper.CategoryListDTO request);

    List<BlogCategory> findAllCategoryList();

    void addCategory(CategoryWrapper.CategoryAddDTO request);

    void modifyCategory(CategoryWrapper.CategoryModifyDTO request);

    void modifyStatusCategory(CategoryWrapper.CategoryModifyStatusDTO request);

    void removeCategory(CategoryWrapper.CategoryDetailDTO request);
}
