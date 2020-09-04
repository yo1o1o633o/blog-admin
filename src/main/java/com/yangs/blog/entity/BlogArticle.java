package com.yangs.blog.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blog_article")
public class BlogArticle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private Integer status;

    private String description;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "view_num")
    private Integer viewNum;

    @Column(name = "archive_time")
    private Integer archiveTime;

    @Column(name = "create_time")
    private Integer createTime;

    @Column(name = "update_time")
    private Integer updateTime;
}
