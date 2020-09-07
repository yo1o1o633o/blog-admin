package com.yangs.blog.service.impl;

import com.yangs.blog.entity.BlogSearchHotWord;
import com.yangs.blog.repository.BlogSearchHotWordRepository;
import com.yangs.blog.service.SearchService;
import com.yangs.blog.wrapper.SearchWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    BlogSearchHotWordRepository blogSearchHotWordRepository;

    @Override
    public List<Object> searchList(SearchWrapper.SearchWordDTO request) {
        BlogSearchHotWord blogSearchHotWord = new BlogSearchHotWord();
        blogSearchHotWord.setWord(request.getName());
        blogSearchHotWordRepository.save(blogSearchHotWord);
        return null;
    }

    @Override
    public List<BlogSearchHotWord> searchHotWordList() {
        return blogSearchHotWordRepository.findAll();
    }
}
