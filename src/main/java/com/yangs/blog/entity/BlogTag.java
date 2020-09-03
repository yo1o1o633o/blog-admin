package com.yangs.blog.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blog_tag")
public class BlogTag {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer status;

    @Column(name = "create_time")
    private Integer createTime;

    @Column(name = "update_time")
    private Integer updateTime;
}
