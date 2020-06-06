package com.feng.book_store_demo.exception;

import com.feng.book_store_demo.constant.enums.AddressEnum;

/**
 * @Author shf
 * @Date 2020/6/5 16:13
 */
public class AddressException extends BaseException {
    public AddressException(AddressEnum e){
        setMsg(e.getMsg());
        setCode(e.getCode());
    }
}
