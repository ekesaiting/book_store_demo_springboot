package com.feng.book_store_demo.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/6/4 21:14
 */
@Data
public class SkuVO {
    private List<SkuTreeVO> tree;
    private List<SpecsNumberVO> list;
    private String price;
    private Integer stock_num;
    private  Boolean none_sku;
    private  Boolean hide_stock;
}
