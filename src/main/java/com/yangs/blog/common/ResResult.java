package com.yangs.blog.common;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;

import java.util.List;

/**
 * @author shuai.yang
 */
@Data
public class ResResult<T> implements Result {
    private Integer status;

    private String desc;

    private T data;

    public ResResult(Integer status, String desc, T data) {
        this.status = status;
        this.desc = desc;
        this.data = data;
    }

    @Override
    public T data() throws Exception {
        if (suc()) {
            return data;
        }
        throw new Exception("没有返回值");
    }

    @Override
    public T nullIfFail() {
        return suc() ? data : null;
    }

    @Override
    public int status() {
        return status;
    }

    @Override
    public String desc() {
        return desc;
    }

    @Override
    public boolean suc() {
        return status == 0;
    }
}
