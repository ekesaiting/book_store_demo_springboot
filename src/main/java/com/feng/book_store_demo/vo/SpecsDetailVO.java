package com.feng.book_store_demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author shf
 * @Date 2020/6/4 21:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecsDetailVO {
    private Integer id;
    private String name;
    private String imgUrl;
    private String previewImgUrl;
}
