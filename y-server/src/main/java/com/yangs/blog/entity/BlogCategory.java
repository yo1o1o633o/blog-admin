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

    @Column(name = "create_time")
    private Integer createTime;

    @Column(name = "update_time")
    private Integer updateTime;
}
