package com.yangs.blog.utils;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shuai.yang
 */
public class SortUtils {
    public static Sort sort(Sort.Direction direction, String column) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(direction, column));
        return Sort.by(orders);
    }
}
