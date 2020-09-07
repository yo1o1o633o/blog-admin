package com.yangs.blog.controller;

import com.yangs.blog.entity.BlogSearchHotWord;
import com.yangs.blog.service.SearchService;
import com.yangs.blog.wrapper.SearchWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:8080"})
public class SearchController {
    @Autowired
    SearchService searchService;

    @PostMapping("/search/list")
    public List<Object> searchArticleList(SearchWrapper.SearchWordDTO request) {
        return searchService.searchList(request);
    }

    @PostMapping("/search/hot/word/list")
    public List<BlogSearchHotWord> searchHotWord() {
        return searchService.searchHotWordList();
    }
}
