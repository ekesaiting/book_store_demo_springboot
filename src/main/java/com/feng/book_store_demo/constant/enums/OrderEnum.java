package com.feng.book_store_demo.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/5/31 22:32
 */
@Getter
@AllArgsConstructor
public enum OrderEnum {
    UNPAID(0,"未支付"),
    PAID(1,"已支付"),
    ORDER_NOT_EXIST(1,"订单不存在"),
    ORDER_FREIGHT(10,"运费价格");
    private final int code;
    private final String msg;
}
