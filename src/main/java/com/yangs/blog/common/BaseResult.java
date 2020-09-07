package com.yangs.blog.common;

import lombok.Data;

@Data
public class BaseResult {
    private Integer status;

    private String desc;

    private ResResult data;
}
