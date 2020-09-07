package com.yangs.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.yangs.blog.service.ArticleService;
import com.yangs.blog.vo.ArchiveListVO;
import com.yangs.blog.vo.ArticleDetailVO;
import com.yangs.blog.wrapper.ArticleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/list/article")
    public String listArticle(@RequestBody @Valid ArticleWrapper.ArticleListDTO request) {
        return JSONObject.toJSONString(articleService.findAllArticle(request));
    }

    @RequestMapping("/add/article")
    public void addArticle(@RequestBody @Valid ArticleWrapper.ArticleAddDTO request) {
        articleService.addArticle(request);
    }

    @RequestMapping("/modify/article")
    public void modifyArticle(@RequestBody @Valid ArticleWrapper.ArticleModifyDTO request) {
        articleService.modifyArticle(request);
    }

    @RequestMapping("/article/modify/status")
    public void modifyArticleStatus(@RequestBody @Valid ArticleWrapper.ArticleModifyStatusDTO request) {
        articleService.modifyStatusArticle(request);
    }

    @RequestMapping("/article/detail")
    public ArticleDetailVO findArticleById(@RequestBody @Valid ArticleWrapper.ArticleDetailDTO request) {
        return articleService.findArticleById(request);
    }

    @RequestMapping("/article/remove")
    public void removeArticle(@RequestBody @Valid ArticleWrapper.ArticleDetailDTO request) {
        articleService.removeArticle(request);
    }

    @RequestMapping("/archive/list")
    public List<ArchiveListVO> findAllArchiveList() {
        return articleService.findAllArchiveList();
    }
}
