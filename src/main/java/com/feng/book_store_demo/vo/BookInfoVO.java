package com.feng.book_store_demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Author shf
 * @Date 2020/6/4 19:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfoVO {
    private Integer id;
    private String title;
    private String price;
    private String desc;
    private List<Map<String,String>> tag;
    private String thumb;
}
