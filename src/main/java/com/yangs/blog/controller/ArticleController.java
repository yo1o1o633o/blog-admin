package com.yangs.blog.controller;

import com.yangs.blog.common.ResResult;
import com.yangs.blog.service.ArticleService;
import com.yangs.blog.vo.ArchiveListVO;
import com.yangs.blog.vo.ArticleDetailVO;
import com.yangs.blog.wrapper.ArticleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/article")
    public ResResult listArticle(Integer page, Integer size) {
        return articleService.findAllArticle(page, size);
    }

    @PostMapping("/article")
    public void addArticle(@RequestBody @Valid ArticleWrapper.ArticleAddDTO request) {
        articleService.addArticle(request);
    }

    @PutMapping("/article")
    public void modifyArticle(@RequestBody @Valid ArticleWrapper.ArticleModifyDTO request) {
        articleService.modifyArticle(request);
    }

    @PutMapping("/article/status")
    public void modifyArticleStatus(@RequestBody @Valid ArticleWrapper.ArticleModifyStatusDTO request) {
        articleService.modifyStatusArticle(request);
    }

    @GetMapping("/article/detail")
    public ArticleDetailVO findArticleById(@Param("id") Integer id) {
        return articleService.findArticleById(id);
    }

    @DeleteMapping("/article")
    public void removeArticle(Integer id) {
        articleService.removeArticle(id);
    }
}
