package com.yangs.blog.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blog_article_tags")
public class BlogArticleTag {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "tag_id")
    private Integer tagId;
}
