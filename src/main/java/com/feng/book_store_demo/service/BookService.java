package com.feng.book_store_demo.service;

import com.feng.book_store_demo.vo.BookIndexVO;
import com.feng.book_store_demo.vo.BookInfoVO;
import com.feng.book_store_demo.vo.BookSpecsVO;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/6/4 19:27
 */

public interface BookService {
    BookIndexVO getBookIndexVO();
    List<BookInfoVO> getBookByCategoryType(Integer type);
    BookSpecsVO getSpecsByBookId(Integer id);
    void subStock(Integer specsId,Integer quantity);
    void addStock(Integer specsId,Integer quantity);
}
