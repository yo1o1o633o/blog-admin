package com.yangs.blog.repository;

import com.yangs.blog.entity.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogTagRepository extends JpaRepository<BlogTag, Integer> {
    List<BlogTag> findAllByStatus(Integer status);
}
