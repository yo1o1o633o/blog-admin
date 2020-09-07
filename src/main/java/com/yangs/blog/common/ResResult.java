package com.yangs.blog.common;

import lombok.Data;

import java.util.List;

@Data
public class ResResult<T> extends BaseResult {
    private Integer count;

    private List<T> row;
}
