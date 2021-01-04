package com.yangs.blog.controller;

import com.yangs.blog.common.PageResult;
import com.yangs.blog.vo.ArticleListVO;
import export.ArticleServiceApi;
import utils.ResResult;
import com.yangs.blog.service.ArticleService;
import com.yangs.blog.utils.ResUtils;
import com.yangs.blog.vo.ArticleDetailVO;
import com.yangs.blog.wrapper.ArticleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author shuai.yang
 */
@RestController
public class ArticleController implements ArticleServiceApi {
    @Autowired
    ArticleService articleService;

    @Override
    public ResResult list(wrapper.ArticleWrapper.ListDTO listDTO) {
        return ResUtils.data(articleService.list(listDTO.getPage(), listDTO.getSize()));
    }

    @PostMapping("/article/a")
    public ResResult addArticle(@RequestBody @Valid ArticleWrapper.ArticleAddDTO request) {
        articleService.add(request);
        return ResUtils.suc();
    }

    @PutMapping("/article/b")
    public ResResult modifyArticle(@RequestBody @Valid ArticleWrapper.ArticleModifyDTO request) {
        articleService.modify(request);
        return ResUtils.suc();
    }

    @PutMapping("/article/status")
    public ResResult modifyArticleStatus(@RequestBody @Valid ArticleWrapper.ArticleModifyStatusDTO request) {
        articleService.modify(request);
        return ResUtils.suc();
    }

    @GetMapping("/article/detail")
    public ResResult<ArticleDetailVO> detail(@Param("id") Integer id) {
        return ResUtils.data(articleService.detail(id));
    }

    @DeleteMapping("/article/c")
    public ResResult removeArticle(Integer id) {
        articleService.remove(id);
        return ResUtils.suc();
    }
}
