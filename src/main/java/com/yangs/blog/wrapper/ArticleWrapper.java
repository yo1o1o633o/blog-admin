package com.yangs.blog.wrapper;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ArticleWrapper {
    @Data
    public static class ArticleListDTO {
        @Min(1)
        private Integer page;
        @Min(1)
        private Integer size;
    }

    @Data
    public static class ArticleAddDTO {
        private String title;

        private String content;
    }

    @Data
    public static class ArticleModifyDTO {
        @Min(1)
        @NotNull
        private Integer id;

        private String title;

        private String content;
    }
}
