package com.feng.book_store_demo.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/5/31 22:25
 */
@Getter
@AllArgsConstructor
public enum BookEnum {
    BOOK_STOCK_ERROR(0,"图书库存不足"),
    BOOK_NOT_EXIST(3,"图书不存在"),
    SPECS_NOT_EXIST(2,"规格不存在");
    private final int code;
    private final String msg;
}
