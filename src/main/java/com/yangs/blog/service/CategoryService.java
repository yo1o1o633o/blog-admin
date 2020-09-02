package com.yangs.blog.service;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.wrapper.CategoryWrapper;

public interface CategoryService {
    ResResult findAllCategoryList(CategoryWrapper.CategoryListDTO request);

    void addCategory(CategoryWrapper.CategoryAddDTO request);

    void modifyCategory(CategoryWrapper.CategoryModifyDTO request);

    void modifyStatusCategory(CategoryWrapper.CategoryModifyStatusDTO request);

    void removeCategory(CategoryWrapper.CategoryDetailDTO request);
}
