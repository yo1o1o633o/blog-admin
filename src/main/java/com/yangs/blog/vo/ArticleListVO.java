package com.yangs.blog.vo;

import lombok.Data;

@Data
public class ArticleListVO {
    private Integer id;

    private String title;

    private String content;

    private Integer viewNum;

    private Integer status;

    private String createTime;

    private String updateTime;
}
