package com.yangs.blog.service.impl;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.entity.BlogCategory;
import com.yangs.blog.repository.BlogCategoryRepository;
import com.yangs.blog.service.CategoryService;
import com.yangs.blog.utils.TimeUtils;
import com.yangs.blog.vo.CategoryListVO;
import com.yangs.blog.wrapper.CategoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    BlogCategoryRepository blogCategoryRepository;

    @Override
    public ResResult findAllCategoryList(CategoryWrapper.CategoryListDTO request) {
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize());
        Page<BlogCategory> categoryList = blogCategoryRepository.findAll(pageRequest);

        List<CategoryListVO> categoryLists = new ArrayList<>();
        for (BlogCategory category : categoryList) {
            CategoryListVO categoryListVO = new CategoryListVO();
            categoryListVO.setName(category.getName());
            categoryListVO.setId(category.getId());
            categoryListVO.setCreateTime(TimeUtils.formatTime(category.getCreateTime()));
            categoryLists.add(categoryListVO);
        }
        long count = blogCategoryRepository.count();

        ResResult<CategoryListVO> resResult = new ResResult<>();
        resResult.setCount((int) count);
        resResult.setRow(categoryLists);
        return resResult;
    }

    @Override
    public void addCategory(CategoryWrapper.CategoryAddDTO request) {
        BlogCategory category = new BlogCategory();
        category.setName(request.getName());
        category.setCreateTime((int) System.currentTimeMillis() / 1000);
        blogCategoryRepository.save(category);
    }

    @Override
    public void modifyCategory(CategoryWrapper.CategoryModifyDTO request) {
        BlogCategory category = blogCategoryRepository.findById(request.getId()).orElse(null);
        if (category == null) {
            return;
        }
        category.setName(request.getName());
        blogCategoryRepository.save(category);
    }

    @Override
    public void removeCategory(CategoryWrapper.CategoryListDTO request) {

    }
}
