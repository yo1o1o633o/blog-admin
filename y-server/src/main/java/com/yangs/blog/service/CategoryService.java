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
    PageResult<CategoryListVO> queryList(CategoryWrapper.CategoryListDTO request);

    List<BlogCategory> list();

    void add(CategoryWrapper.CategoryAddDTO request);

    void modify(CategoryWrapper.CategoryModifyDTO request);

    void modify(CategoryWrapper.CategoryModifyStatusDTO request);

    void remove(CategoryWrapper.CategoryDetailDTO request);
}
