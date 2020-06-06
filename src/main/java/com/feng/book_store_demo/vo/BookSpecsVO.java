package com.feng.book_store_demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author shf
 * @Date 2020/6/4 21:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSpecsVO {
    private Map<String,String> goods;
    private SkuVO sku;
}
