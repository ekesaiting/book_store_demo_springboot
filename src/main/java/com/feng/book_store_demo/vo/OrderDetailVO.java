package com.feng.book_store_demo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailVO {
    private String orderId;
    private String buyerName;
    private String tel;
    private String address;
    private Integer num;
    private String bookName;
    private String specs;
    private String price;
    private String icon;
    private BigDecimal amount;
    private Integer payStatus;
    private Integer freight = 10;
}
