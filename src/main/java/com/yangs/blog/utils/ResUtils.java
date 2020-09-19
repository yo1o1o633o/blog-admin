package com.yangs.blog.utils;

import com.yangs.blog.common.ResResult;

/**
 * @author shuai.yang
 */
public final class ResUtils {
    private static final String SUC_DESC = Boolean.TRUE.toString();
    private static final int SUC = 0;
    private static final int FAIL = 10000;
    private static final String FAIL_DESC = Boolean.FALSE.toString();

    public static ResResult suc(String data) {
        return result(SUC, SUC_DESC, data);
    }

    public static <T>ResResult<T> data(T data) {
        return result(SUC, SUC_DESC, data);
    }

    public static ResResult suc() {
        return suc(null);
    }

    public static ResResult fail(String desc) {
        return fail(FAIL, desc);
    }

    public static ResResult fail(int code, String desc) {
        return result(code, desc, null);
    }

    private static <T> ResResult<T> result(int code, String desc, T data) {
        return new ResResult<>(code, desc, data);
    }
}
