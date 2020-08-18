package com.yangs.blog.repository;

import com.yangs.blog.entity.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogTagRepository extends JpaRepository<BlogTag, Integer> {
}
