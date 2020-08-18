package com.yangs.blog.common;

import lombok.Data;

import java.util.List;

@Data
public class ResResult<T> {
    private Integer count;

    private List<T> row;
}
