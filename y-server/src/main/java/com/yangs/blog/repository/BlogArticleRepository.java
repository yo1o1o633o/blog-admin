package com.yangs.blog.repository;

import com.yangs.blog.entity.BlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogArticleRepository extends JpaRepository<BlogArticle, Integer> {

}
