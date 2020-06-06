package com.feng.book_store_demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author shf
 * @Date 2020/6/4 21:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecsNumberVO {
    private Integer s1;
    private BigDecimal price;
    private Integer Stock_num;
}
