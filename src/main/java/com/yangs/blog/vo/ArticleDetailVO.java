package com.yangs.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author shuai.yang
 */
@Data
public class ArticleDetailVO {
    private Integer id;

    private String title;

    private String content;

    private String description;

    private Integer categoryId;

    private Integer archiveTime;

    private List<Integer> tagList;

    private List<String> tagNameList;

    private String archiveTimeStr;
}
