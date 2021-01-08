package com.yangs.blog.wrapper;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ArticleWrapper {
    @Data
    public static class ArticleAddDTO {
        private String title;

        private Integer categoryId;

        private Integer archiveTime;

        private List<Integer> tagId;

        private String description;

        private String content;
    }

    @Data
    public static class ArticleModifyDTO {
        @Min(1)
        @NotNull
        private Integer id;

        private String title;

        private String content;

        private Integer archiveTime;

        private String description;

        private List<Integer> tagIds;

        private Integer categoryId;
    }

    @Data
    public static class ArticleModifyStatusDTO {
        @Min(1)
        @NotNull
        private Integer id;

        private Integer status;
    }
}
