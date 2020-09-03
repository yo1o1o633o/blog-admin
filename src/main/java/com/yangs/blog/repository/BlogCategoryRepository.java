package com.yangs.blog.repository;

import com.yangs.blog.entity.BlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Integer> {
    List<BlogCategory> findAllByStatus(Integer status);
}
