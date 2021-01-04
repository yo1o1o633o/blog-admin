package com.yangs.blog.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blog_search_hot_word")
public class BlogSearchHotWord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String word;
}
