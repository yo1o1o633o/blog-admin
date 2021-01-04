package com.yangs.blog.controller;

import utils.ResResult;
import com.yangs.blog.entity.BlogSearchHotWord;
import com.yangs.blog.service.SearchService;
import com.yangs.blog.utils.ResUtils;
import com.yangs.blog.wrapper.SearchWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shuai.yang
 */
@RestController
public class SearchController {
    @Autowired
    SearchService searchService;

    @PostMapping("/search/list")
    public ResResult<List<Object>> searchArticleList(SearchWrapper.SearchWordDTO request) {
        return ResUtils.data(searchService.searchList(request));
    }

    @PostMapping("/search/hot/word/list")
    public ResResult<List<BlogSearchHotWord>> searchHotWord() {
        return ResUtils.data(searchService.searchHotWordList());
    }
}
