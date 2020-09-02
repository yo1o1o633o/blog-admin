package com.yangs.blog.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blog_category")
public class BlogCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    private String name;

    private Integer createTime;

    private Integer updateTime;
}
