package com.feng.book_store_demo.vo;

import com.feng.book_store_demo.entity.BookCategory;
import com.feng.book_store_demo.entity.BookInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/6/4 19:17
 */
@Data
public class BookIndexVO {
    private List<BookCategoryVO> categories;
    private List<BookInfoVO> books;
}
