package com.feng.book_store_demo.service.impl;

import com.feng.book_store_demo.constant.enums.OrderEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author shf
 * @Date 2020/6/6 20:47
 */
@SpringBootTest
class OrderServiceImplTest {
    @Test
    public void test(){
        BigDecimal b1 = BigDecimal.valueOf(10450 / 100.0);
        System.out.println(b1);
        BigDecimal b2 = b1.multiply(BigDecimal.valueOf(100));
        System.out.println(b2);
        BigDecimal b3 = b2.add(BigDecimal.valueOf(OrderEnum.ORDER_FREIGHT.getCode()));
        System.out.println(b3);

    }

}