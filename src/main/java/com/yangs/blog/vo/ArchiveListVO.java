package com.yangs.blog.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArchiveListVO {
    private String year;

    private List<ArchiveItem> archiveItem;
}
