package com.yangs.blog.utils;

import com.yangs.blog.common.BaseResult;

public class ResUtils {
    public static BaseResult suc() {
        BaseResult result = new BaseResult();
        result.setStatus(200);
        result.setDesc("成功");
        return result;
    }


}
