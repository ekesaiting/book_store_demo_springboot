package com.feng.book_store_demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author shf
 * @Date 2020/6/4 19:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCategoryVO {
    private String name;
    private Integer type;
}
