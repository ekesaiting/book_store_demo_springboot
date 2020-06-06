package com.feng.book_store_demo.exception;


import com.feng.book_store_demo.constant.enums.BookEnum;

/**
 * @Author shf
 * @Date 2020/5/31 22:23
 */
public class BookException extends BaseException {
    public BookException(BookEnum e){
        setMsg(e.getMsg());
        setCode(e.getCode());
    }
}
