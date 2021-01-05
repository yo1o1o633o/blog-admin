package wrapper;

import lombok.Data;

import java.util.List;

/**
 * @author shuai.yang
 */
public class ArticleWrapper {

    @Data
    public static class ListDTO {
        private Integer page;

        private Integer size;
    }

    @Data
    public static class AddDTO {
        private String title;

        private Integer categoryId;

        private Integer archiveTime;

        private List<Integer> tagId;

        private String description;

        private String content;
    }
}
