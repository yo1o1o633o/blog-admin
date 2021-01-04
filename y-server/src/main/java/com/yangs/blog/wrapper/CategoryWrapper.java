package com.yangs.blog.wrapper;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CategoryWrapper {
    @Data
    public static class CategoryListDTO {
        @Min(1)
        @NotNull
        private Integer page;
        @Min(1)
        @NotNull
        private Integer size;
    }

    @Data
    public static class CategoryAddDTO {
        @NotNull
        private String name;
    }

    @Data
    public static class CategoryModifyDTO {
        @Min(1)
        @NotNull
        private Integer id;

        @NotNull
        private String name;
    }

    @Data
    public static class CategoryModifyStatusDTO {
        @Min(1)
        @NotNull
        private Integer id;

        @NotNull
        private Integer status;
    }

    @Data
    public static class CategoryDetailDTO {
        @Min(1)
        @NotNull
        private Integer id;
    }
}
