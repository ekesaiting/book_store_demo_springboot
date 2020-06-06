package com.feng.book_store_demo.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feng.book_store_demo.constant.enums.ResultEnum;
import com.feng.book_store_demo.service.BookService;
import com.feng.book_store_demo.util.StringFormat;
import com.feng.book_store_demo.vo.BookIndexVO;
import com.feng.book_store_demo.vo.BookInfoVO;
import com.feng.book_store_demo.vo.BookSpecsVO;
import com.feng.book_store_demo.vo.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/6/4 20:15
 */
@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    private final ObjectMapper mapper=new ObjectMapper();

    private void print(Object o) {
        try {
            System.out.println(StringFormat.formatToJson(mapper.writeValueAsString(o)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    @Test
    void getBookIndexVO()  {
        BookIndexVO bookIndexVO = bookService.getBookIndexVO();
        ResultVO<BookIndexVO> resultVO = new ResultVO<BookIndexVO>(ResultEnum.SUCCESS,bookIndexVO);
        print(resultVO);
    }
    @Test
    void getBookByCategoryId(){
        List<BookInfoVO> book = bookService.getBookByCategoryType(1);
        print(book);
    }

    @Test
    public void getSpecsByBookId(){
        BookSpecsVO specsByBookId = bookService.getSpecsByBookId(1);
        print(specsByBookId);
    }
}