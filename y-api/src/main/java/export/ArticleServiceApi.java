package export;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import utils.ResResult;
import wrapper.ArticleWrapper;

/**
 * @author shuai.yang
 */
public interface ArticleServiceApi {
    @GetMapping("/article")
    ResResult list(ArticleWrapper.ListDTO listDTO);

    @PostMapping("/article/add")
    ResResult add(ArticleWrapper.AddDTO addDTO);
}
