package com.yangs.blog.service.impl;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.service.CategoryService;
import com.yangs.blog.wrapper.CategoryWrapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public ResResult findAllCategoryList(CategoryWrapper.CategoryListDTO request) {
        return null;
    }

    @Override
    public void addCategory(CategoryWrapper.CategoryAddDTO request) {

    }

    @Override
    public void modifyCategory(CategoryWrapper.CategoryModifyDTO request) {

    }

    @Override
    public void removeCategory(CategoryWrapper.CategoryListDTO request) {

    }
}
