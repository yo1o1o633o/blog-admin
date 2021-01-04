package wrapper;

import lombok.Data;

/**
 * @author shuai.yang
 */
public class ArticleWrapper {

    @Data
    public static class ListDTO {
        private Integer page;

        private Integer size;
    }
}
