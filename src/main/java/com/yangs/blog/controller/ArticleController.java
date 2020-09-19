package com.yangs.blog.controller;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.service.ArticleService;
import com.yangs.blog.utils.ResUtils;
import com.yangs.blog.vo.ArchiveListVO;
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
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/article")
    public ResResult listArticle(Integer page, Integer size) {
        return ResUtils.data(articleService.findAllArticle(page, size));
    }

    @PostMapping("/article")
    public ResResult addArticle(@RequestBody @Valid ArticleWrapper.ArticleAddDTO request) {
        articleService.addArticle(request);
        return ResUtils.suc();
    }

    @PutMapping("/article")
    public ResResult modifyArticle(@RequestBody @Valid ArticleWrapper.ArticleModifyDTO request) {
        articleService.modifyArticle(request);
        return ResUtils.suc();
    }

    @PutMapping("/article/status")
    public ResResult modifyArticleStatus(@RequestBody @Valid ArticleWrapper.ArticleModifyStatusDTO request) {
        articleService.modifyStatusArticle(request);
        return ResUtils.suc();
    }

    @GetMapping("/article/detail")
    public ResResult<ArticleDetailVO> findArticleById(@Param("id") Integer id) {
        return ResUtils.data(articleService.findArticleById(id));
    }

    @DeleteMapping("/article")
    public ResResult removeArticle(Integer id) {
        articleService.removeArticle(id);
        return ResUtils.suc();
    }
}
