package com.feng.book_store_demo.util;

import java.util.*;

/**
 * @Author shf
 * @Date 2020/6/4 20:00
 */
public class TagsSplitUtil {
    public static List<Map<String, String>> split(String s) {
        String[] strings = s.split("\\|");
        List<Map<String, String>> list = new ArrayList<>();
        for (String str : strings) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name",str);
            list.add(map);
        }
        return list;
    }
}
