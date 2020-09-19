package com.yangs.blog.service.impl;

import com.yangs.blog.common.PageResult;
import com.yangs.blog.common.ResResult;
import com.yangs.blog.entity.BlogCategory;
import com.yangs.blog.repository.BlogCategoryRepository;
import com.yangs.blog.service.CategoryService;
import com.yangs.blog.utils.SortUtils;
import com.yangs.blog.utils.TimeUtils;
import com.yangs.blog.vo.CategoryListVO;
import com.yangs.blog.wrapper.CategoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    BlogCategoryRepository categoryRepository;

    @Override
    public PageResult<CategoryListVO> findAllCategory(CategoryWrapper.CategoryListDTO request) {
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), SortUtils.sort(Sort.Direction.DESC, "id"));
        Page<BlogCategory> categoryList = categoryRepository.findAll(pageRequest);

        List<CategoryListVO> categoryLists = new ArrayList<>();
        for (BlogCategory category : categoryList) {
            CategoryListVO categoryListVO = new CategoryListVO();
            categoryListVO.setName(category.getName());
            categoryListVO.setStatus(category.getStatus());
            categoryListVO.setId(category.getId());
            categoryListVO.setCreateTime(TimeUtils.formatTime(category.getCreateTime()));
            categoryLists.add(categoryListVO);
        }
        long count = categoryRepository.count();

        PageResult<CategoryListVO> resResult = new PageResult<>();
        resResult.setTotal(count);
        resResult.setRows(categoryLists);
        return resResult;
    }

    @Override
    public List<BlogCategory> findAllCategoryList() {
        return categoryRepository.findAllByStatus(1);
    }

    @Override
    public void addCategory(CategoryWrapper.CategoryAddDTO request) {
        BlogCategory category = new BlogCategory();
        category.setStatus(1);
        category.setName(request.getName());
        category.setCreateTime(TimeUtils.getCurrentTime());
        category.setUpdateTime(TimeUtils.getCurrentTime());
        categoryRepository.save(category);
    }

    @Override
    public void modifyCategory(CategoryWrapper.CategoryModifyDTO request) {
        BlogCategory category = categoryRepository.findById(request.getId()).orElse(null);
        if (category == null) {
            return;
        }
        category.setName(request.getName());
        category.setUpdateTime(TimeUtils.getCurrentTime());
        categoryRepository.save(category);
    }

    @Override
    public void modifyStatusCategory(CategoryWrapper.CategoryModifyStatusDTO request) {
        BlogCategory category = categoryRepository.findById(request.getId()).orElse(null);
        if (category != null) {
            category.setStatus(request.getStatus());
            categoryRepository.save(category);
        }
    }

    @Override
    public void removeCategory(CategoryWrapper.CategoryDetailDTO request) {
        categoryRepository.deleteById(request.getId());
    }
}
