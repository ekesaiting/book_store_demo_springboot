package com.feng.book_store_demo.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/5/31 21:32
 */
@Getter
@AllArgsConstructor
public enum BusinessExceptionEnum {
    PHONE_STOCK_ERROR(0,"手机库存不足"),
    PHONE_NOT_EXIST(3,"手机不存在"),
    ORDER_NOT_EXIST(1,"订单不存在"),
    SPECS_NOT_EXIST(2,"规格不存在");
    private final int code;
    private final String msg;
}
