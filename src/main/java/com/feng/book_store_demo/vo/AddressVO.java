package com.feng.book_store_demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author shf
 * @Date 2020/6/5 16:01
 */
@Data
@AllArgsConstructor
public class AddressVO {
        private Integer id;
        private String areaCode;
        private String name;
        private String tel;
        private String address;
}
