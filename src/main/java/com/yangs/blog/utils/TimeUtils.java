package com.yangs.blog.utils;

import java.text.SimpleDateFormat;

public class TimeUtils {
    public static String formatTime(Integer time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(time * 1000L);
    }

    public static String formatTimeToDay(Integer time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        return simpleDateFormat.format(time * 1000L);
    }
}
