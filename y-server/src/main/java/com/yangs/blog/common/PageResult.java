package com.yangs.blog.common;

import lombok.Data;

import java.util.List;

/**
 * @author shuai.yang
 */
@Data
public class PageResult<T> {
    private Long total;

    private List<T> rows;
}
