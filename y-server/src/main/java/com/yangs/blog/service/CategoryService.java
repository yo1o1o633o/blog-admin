package com.yangs.blog.service;

import com.yangs.blog.common.PageResult;
import com.yangs.blog.entity.BlogCategory;
import com.yangs.blog.vo.CategoryListVO;
import com.yangs.blog.wrapper.CategoryWrapper;

import java.util.List;

/**
 * @author shuai.yang
 */
public interface CategoryService {
    PageResult<CategoryListVO> findAllCategory(CategoryWrapper.CategoryListDTO request);

    List<BlogCategory> findAllCategoryList();

    void addCategory(CategoryWrapper.CategoryAddDTO request);

    void modifyCategory(CategoryWrapper.CategoryModifyDTO request);

    void modifyStatusCategory(CategoryWrapper.CategoryModifyStatusDTO request);

    void removeCategory(CategoryWrapper.CategoryDetailDTO request);
}
