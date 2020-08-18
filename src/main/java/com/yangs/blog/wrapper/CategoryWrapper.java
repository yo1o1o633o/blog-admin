package com.yangs.blog.wrapper;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CategoryWrapper {
    @Data
    public static class CategoryListDTO {
        @Min(1)
        private Integer page;
        @Min(1)
        private Integer size;
    }

    @Data
    public static class CategoryAddDTO {
        private String name;
    }

    @Data
    public static class CategoryModifyDTO {
        @Min(1)
        @NotNull
        private Integer id;

        private String name;
    }
}
