package com.yangs.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.yangs.blog.service.ArticleService;
import com.yangs.blog.wrapper.ArticleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = {"http://localhost:8080"})
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
}
