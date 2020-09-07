package com.yangs.blog.repository;


import com.yangs.blog.entity.BlogArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogArticleTagRepository extends JpaRepository<BlogArticleTag, Integer> {
    List<BlogArticleTag> findAllByArticleId(Integer articleId);

    void deleteAllByArticleId(Integer id);
}
