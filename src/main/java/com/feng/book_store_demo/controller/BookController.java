package com.feng.book_store_demo.controller;

import com.feng.book_store_demo.constant.enums.ResultEnum;
import com.feng.book_store_demo.service.BookService;
import com.feng.book_store_demo.vo.BookIndexVO;
import com.feng.book_store_demo.vo.BookInfoVO;
import com.feng.book_store_demo.vo.BookSpecsVO;
import com.feng.book_store_demo.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/6/5 9:57
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/index")
    public ResultVO<BookIndexVO>  index(){
        BookIndexVO bookIndexVO = bookService.getBookIndexVO();
        return new ResultVO<>(ResultEnum.SUCCESS,bookIndexVO);
    }
    @GetMapping("/getBookByCategoryType/{type}")
    public ResultVO<List<BookInfoVO>> getBookByCategoryType(@PathVariable("type") Integer type){
        List<BookInfoVO> books = bookService.getBookByCategoryType(type);
        return new ResultVO<>(ResultEnum.SUCCESS,books);
    }
    @GetMapping("/getSpecsByBookId/{id}")
    public ResultVO<BookSpecsVO> getSpecsByBookId(@PathVariable("id") Integer id){
        BookSpecsVO specs = bookService.getSpecsByBookId(id);
        return new ResultVO<>(ResultEnum.SUCCESS,specs);
    }
}
