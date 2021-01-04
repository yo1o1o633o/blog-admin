package com.yangs.blog.service;

import com.yangs.blog.entity.BlogSearchHotWord;
import com.yangs.blog.wrapper.SearchWrapper;

import java.util.List;

public interface SearchService {
    List<Object> searchList(SearchWrapper.SearchWordDTO request);

    List<BlogSearchHotWord> searchHotWordList();
}
