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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    BlogCategoryRepository blogCategoryRepository;

    @Override
    public ResResult findAllCategory(CategoryWrapper.CategoryListDTO request) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
        Sort sort = Sort.by(orders);

        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        Page<BlogCategory> categoryList = blogCategoryRepository.findAll(pageRequest);

        List<CategoryListVO> categoryLists = new ArrayList<>();
        for (BlogCategory category : categoryList) {
            CategoryListVO categoryListVO = new CategoryListVO();
            categoryListVO.setName(category.getName());
            categoryListVO.setStatus(category.getStatus());
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
    public List<BlogCategory> findAllCategoryList() {
        return blogCategoryRepository.findAllByStatus(1);
    }

    @Override
    public void addCategory(CategoryWrapper.CategoryAddDTO request) {
        BlogCategory category = new BlogCategory();
        category.setStatus(1);
        category.setName(request.getName());
        category.setCreateTime((int) (System.currentTimeMillis() / 1000));
        category.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        blogCategoryRepository.save(category);
    }

    @Override
    public void modifyCategory(CategoryWrapper.CategoryModifyDTO request) {
        BlogCategory category = blogCategoryRepository.findById(request.getId()).orElse(null);
        if (category == null) {
            return;
        }
        category.setName(request.getName());
        category.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        blogCategoryRepository.save(category);
    }

    @Override
    public void modifyStatusCategory(CategoryWrapper.CategoryModifyStatusDTO request) {
        BlogCategory category = blogCategoryRepository.findById(request.getId()).orElse(null);
        if (category != null) {
            category.setStatus(request.getStatus());
            blogCategoryRepository.save(category);
        }
    }

    @Override
    public void removeCategory(CategoryWrapper.CategoryDetailDTO request) {
        blogCategoryRepository.deleteById(request.getId());
    }
}
