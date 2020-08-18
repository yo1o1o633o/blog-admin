package com.yangs.blog.wrapper;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TagWrapper {
    @Data
    public static class TagListDTO {
        @Min(1)
        private Integer page;
        @Min(1)
        private Integer size;
    }

    @Data
    public static class TagAddDTO {
        private String name;
    }

    @Data
    public static class TagModifyDTO {
        @Min(1)
        @NotNull
        private Integer id;

        private String name;
    }
}
