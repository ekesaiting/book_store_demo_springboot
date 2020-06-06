package com.feng.book_store_demo.exception;


import com.feng.book_store_demo.constant.enums.OrderEnum;

/**
 * @Author shf
 * @Date 2020/5/31 22:37
 */
public class OrderException extends BaseException {
    public OrderException(OrderEnum e){
        setCode(e.getCode());
        setMsg(e.getMsg());
    }

    public static void main(String[] args) {
        OrderException exception = new OrderException(OrderEnum.ORDER_NOT_EXIST);
        System.out.println(exception.getCode());
        System.out.println(exception.getMsg());
    }
}
