package com.feng.book_store_demo.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author shf
 * @Date 2020/6/4 21:15
 */
@Data
public class SkuTreeVO {
        private final String k="选项";
        private List<SpecsDetailVO> v;
        private final String k_s="s1";
}
