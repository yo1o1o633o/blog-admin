package export;

import org.springframework.web.bind.annotation.GetMapping;
import utils.ResResult;
import wrapper.ArticleWrapper;

import java.util.List;

/**
 * @author shuai.yang
 */
public interface ArticleServiceApi {
    @GetMapping("/article")
    ResResult list(ArticleWrapper.ListDTO listDTO);
}
