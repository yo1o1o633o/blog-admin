package com.yangs.blog.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleListVO {
    private Integer id;

    private String title;

    private String description;

    private String content;

    private Integer viewNum;

    private Integer status;

    private String categoryName;

    private List<ArticleTagListVO> tagList;

    private String createTime;

    private String updateTime;

    private String archiveTime;
}
