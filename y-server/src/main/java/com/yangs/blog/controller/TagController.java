package com.yangs.blog.controller;

import com.yangs.blog.common.PageResult;
import utils.ResResult;
import com.yangs.blog.entity.BlogTag;
import com.yangs.blog.service.TagService;
import com.yangs.blog.utils.ResUtils;
import com.yangs.blog.vo.TagListVO;
import com.yangs.blog.wrapper.TagWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author shuai.yang
 */
@RestController
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping("/tag")
    public ResResult<List<BlogTag>> findAllTagList() {
        return ResUtils.data(tagService.findAllTag());
    }

    @PostMapping("/tag/list")
    public ResResult<PageResult<TagListVO>> listTag(@RequestBody @Valid TagWrapper.TagListDTO request) {
        return ResUtils.data(tagService.queryList(request));
    }

    @PostMapping("/tag/add")
    public ResResult addTag(@RequestBody @Valid TagWrapper.TagAddDTO request) {
        tagService.add(request);
        return ResUtils.suc();
    }

    @PostMapping("/tag/modify")
    public ResResult modifyTag(@RequestBody @Valid TagWrapper.TagModifyDTO request) {
        tagService.modify(request);
        return ResUtils.suc();
    }

    @PostMapping("/tag/modify/status")
    public ResResult modifyStatusTag(@RequestBody @Valid TagWrapper.TagModifyStatusDTO request) {
        tagService.modify(request);
        return ResUtils.suc();
    }

    @PostMapping("/tag/remove")
    public ResResult remove(@RequestBody @Valid TagWrapper.TagDetailDTO request) {
        tagService.remove(request);
        return ResUtils.suc();
    }
}
