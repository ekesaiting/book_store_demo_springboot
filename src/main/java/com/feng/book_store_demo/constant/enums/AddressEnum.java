package com.feng.book_store_demo.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/6/5 16:14
 */
@Getter
@AllArgsConstructor
public enum AddressEnum {
    ADDRESS_NOT_EXIST(1,"地址不存在");
    private final int code;
    private final String msg;
}
